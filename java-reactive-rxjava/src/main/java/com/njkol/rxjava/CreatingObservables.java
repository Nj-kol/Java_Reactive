package com.njkol.rxjava;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

public class CreatingObservables {

	/**
	 * In this method we've created an integer observable using Observable's static
	 * method just() This method converts the items provided to an Observable So
	 * later we can subscribe and get the item one by one to take action
	 */
	public void createObservableWithJust() {
		Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);

		observable.subscribe(item -> System.out.println(item));
	}

	/**
	 * In this method we've created an integer observable using Observable's static
	 * method fromIterable() This method converts anything which extend or implement
	 * iterable, to an observable So later we can subscribe and get the item one by
	 * one to take action
	 */
	public void createObservableFromIterable() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Observable<Integer> observable = Observable.fromIterable(list);

		observable.subscribe(item -> System.out.println(item));
	}

	/**
	 * In this method we've created an integer observable using Observable's static
	 * method create() Emitter here is an interface By calling it's onNext(),
	 * onComplete() and onError() method we can push events to our subscribers So
	 * our subscribers can subscribe and get the item one by one to take action
	 */
	public void createObservableUsingCreate() {
		Observable<Integer> observable = Observable.create(emitter -> {
			emitter.onNext(1);
			emitter.onNext(2);
			emitter.onNext(3);
			emitter.onNext(4);
			emitter.onNext(5);
			emitter.onNext(null);
			emitter.onComplete();
		});

		observable.subscribe(item -> System.out.println(item),
				error -> System.out.println("There was error: " + error.getLocalizedMessage()),
				() -> System.out.println("Completed"));
	}

}
