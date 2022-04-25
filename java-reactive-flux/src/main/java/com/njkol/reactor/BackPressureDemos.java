package com.njkol.reactor;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class BackPressureDemos {

	private static final class BpSubscriber implements Subscriber<Integer> {
		
		private final List<Integer> elements;
		private Subscription s;
		int onNextAmount;

		private BpSubscriber(List<Integer> elements) {
			this.elements = elements;
		}

		@Override
		public void onSubscribe(Subscription s) {
			this.s = s;
			s.request(2);
		}

		@Override
		public void onNext(Integer integer) {
			elements.add(integer);
			onNextAmount++;
			if (onNextAmount % 2 == 0) {
				s.request(2);
			}
		}

		@Override
		public void onError(Throwable t) {
		}

		@Override
		public void onComplete() {
		}
	}

	public static void demo1() {

		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4).log().subscribe(new BpSubscriber(elements));
	}
}
