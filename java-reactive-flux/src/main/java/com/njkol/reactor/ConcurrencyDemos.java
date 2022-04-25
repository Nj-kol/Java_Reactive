package com.njkol.reactor;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class ConcurrencyDemos {

	public static void demos() throws InterruptedException {

		List<Integer> elements = new ArrayList<>();
		
		Flux.just(1, 2, 3, 4)
		  .log()
		  .map(i -> i * 2)
		  .subscribeOn(Schedulers.parallel())
		  .subscribe(elements::add);
	}
}
