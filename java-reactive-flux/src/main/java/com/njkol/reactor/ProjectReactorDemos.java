package com.njkol.reactor;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProjectReactorDemos {

	public static void main(String[] args) throws InterruptedException {
	
		//BackPressureDemos.demo1();
		
		
		// OperatorsDemos.mapDemo();
		//OperatorsDemos.combineDemo();
		
		// HotStreamDemos.demos();
		HotStreamDemos.demoThrottling();
	}

	public static void dm() {
		
		Flux<Integer> fl = Flux.just(1, 2, 3, 4);
		
		Mono<Integer> mn = Mono.just(1);
		
	//	Publisher<String> p = Mono.just("foo");
		
		
		List<Integer> elements = new ArrayList<>();

		Flux.just(1, 2, 3, 4)
		  .log()
		  .subscribe(elements::add);
		
		
		Flux.just(1, 2, 3, 4)
		  .log()
		  .subscribe(new Subscriber<Integer>() {
		    @Override
		    public void onSubscribe(Subscription s) {
		      s.request(Long.MAX_VALUE);
		    }

		    @Override
		    public void onNext(Integer integer) {
		      elements.add(integer);
		    }

		    @Override
		    public void onError(Throwable t) {}

		    @Override
		    public void onComplete() {}
		});

	}
}
