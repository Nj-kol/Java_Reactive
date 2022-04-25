package com.njkol.reactor;

import java.time.Duration;

import reactor.core.Disposable;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

public class HotStreamDemos {

	public static void demos() throws InterruptedException {

		ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
			while (true) {
				fluxSink.next(System.currentTimeMillis());
			}
		}).publish();

		publish.subscribe(System.out::println);
		publish.subscribe(System.out::println);

		Disposable d = publish.connect();

	}

	public static void demoThrottling() throws InterruptedException {

		ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
		    while(true) {
		        fluxSink.next(System.currentTimeMillis());
		    }
		})
		  .sample(Duration.ofSeconds(2))
		  .publish();;

		publish.subscribe(System.out::println);
		publish.subscribe(System.out::println);

		Disposable d = publish.connect();

	}
}
