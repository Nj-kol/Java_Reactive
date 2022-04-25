package com.njkol.reactor;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import reactor.core.publisher.Flux;

public class WindowingDemos {

	// creditcard stream 1
	 Flux<Integer> transactions1 = Flux.just(100, 101, 102, 100, 105, 102, 104)
			.delayElements(Duration.ofMillis(500));

	// creditcard stream 2
	 Flux<Integer> transactions2 = Flux.just(101, 200, 201, 300, 102, 301, 100)
			.delayElements(Duration.ofMillis(600));

	public static void demoSplitFLux() {

		Flux.range(1, 10).window(5)
				.doOnNext(flux -> flux.collectList().subscribe(l -> System.out.println("Received :: " + l)))
				.subscribe();
	}

	// business rule for fraud detection
	private void fraudDetector(Flux<Integer> transactions) {
		
		transactions.collectList()
			.map(l -> l.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
			.doOnNext(map -> map.entrySet().removeIf(entry -> entry.getValue() < 3)).filter(map -> !map.isEmpty())
			.map(Map::keySet)
			.subscribe(s -> System.out.println("Fraud Cards :: " + s));
	}

	public  void fraudDetectionDemo() {
		// Flux windowing
		Flux.merge(transactions1, transactions2)
		.window(Duration.ofSeconds(2), Duration.ofMillis(500)) // create a flux of 2 secondsevery 500  milliseconds
		.doOnNext(this::fraudDetector).subscribe();
	}
}
