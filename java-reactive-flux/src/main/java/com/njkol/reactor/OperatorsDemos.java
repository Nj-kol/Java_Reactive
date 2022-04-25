package com.njkol.reactor;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

public class OperatorsDemos {

	public static void mapDemo() {
		
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
		  .log()
		  .map(i -> i * 2)
		  .subscribe(elements::add);
		
		elements.forEach(System.out::println);
	}
	
	public static void combineDemo() {
		
		List<String> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
		  .log()
		  .map(i -> i * 2)
		  .zipWith(Flux.range(0, Integer.MAX_VALUE), 
		    (one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
		  .subscribe(elements::add);
		
		elements.forEach(System.out::println);
	}
}
