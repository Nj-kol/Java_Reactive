package com.njkol.reactor;

import java.time.Duration;

import reactor.core.publisher.Flux;

public class BufferingDemos {

	public static void demoBufferByCount() {
		Flux.range(1,20)
        .delayElements(Duration.ofMillis(500))
        .buffer(5)  // collect the items in batches of 5
        .subscribe(l -> System.out.println("Received :: " + l));
	}
	

	public static void demoBufferByTime() {
		Flux.range(1,20)
        .delayElements(Duration.ofMillis(500))
        .buffer(Duration.ofSeconds(3))  // collect the items every 3 seconds
        .subscribe(l -> System.out.println("Received :: " + l));
	}
	
	public static void demoBufferHybrid() {
		
		Flux<Integer> elements1 = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(500));

		Flux<Integer> elements2 = Flux.range(101, 10)
		                .delayElements(Duration.ofMillis(600));

		Flux.merge(elements1, elements2)
		.bufferTimeout(5, Duration.ofSeconds(3))  // collect 5 items every 3 seconds
		.subscribe(l -> System.out.println("Received :: " + l));
	}
	
	
	public static void demoOverlappingBuffer() {
		
		Flux.range(1, 10)
        .buffer(3, 2)  // collect 3, skip 2 then collect 3
        .subscribe(l -> System.out.println("Received :: " + l));
	}
	
	
	public static void demoDroppingBuffer() {
		
		Flux.range(1, 10)
        .buffer(3, 5) // collect 3 for every 5 items
        .subscribe(l -> System.out.println("Received :: " + l));
	}
	
	
	public static void demoDeDupeBuffer() {
		
		Flux.just(1,2,2,2,3,2,2,3,3,3,4,5)
		   .bufferUntilChanged()
		   .subscribe(l -> System.out.println("Received :: " + l));
	}
	
}
