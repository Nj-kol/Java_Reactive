package com.njkol.rxjava.multhithreaded;

import com.njkol.rxjava.NetworkClient;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SchedulersDemos {

	public static void print(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " : " + message);
    }
	
	
	
	
	public static void demoFlatMap() {

        NetworkClient networkClient = new NetworkClient();
        
        String[] usernames = new String[] {
            "john", "mike", "jacob"
        };
        
        Observable.fromArray(usernames)
        .subscribeOn(Schedulers.io())
        .flatMap(username ->
            Observable.fromCallable(() ->
                networkClient.fetchUser(username)
            ).subscribeOn(Schedulers.io())
        )
        .subscribe(user -> print("Got user: " + user.username));
        
	    try{
			Thread.sleep(3000);
		}
		 catch (InterruptedException e) {
		}     
	}
	
	public static void demoOnObserve() {
		
		  Observable<Integer> observable = Observable.create(source -> {
	            print("In subscribe");
	            source.onNext(1);
	            source.onNext(2);
	            source.onNext(3);
	        });
		 

	        observable.subscribeOn(Schedulers.computation()) // "RxComputationThreadPool-1"
	            .doOnNext(value -> print("(a) : " + value))
	            .observeOn(Schedulers.newThread()) // "RxNewThreadScheduler-2"
	            .doOnNext(value -> print("(b) : " + value))
	            .observeOn(Schedulers.newThread()) // "RxNewThreadScheduler-3"
	            .subscribeOn(Schedulers.newThread()) // This has no effect.
	            .doOnNext(value -> print("(c) : " + value))
	            .observeOn(Schedulers.newThread()) // Overwritten by next observeOn().
	            .observeOn(Schedulers.newThread()) // "RxNewThreadScheduler-5"
	            .subscribe(value -> print("(d) : " + value));
	        try{
				Thread.sleep(100);
			}
			 catch (InterruptedException e) {
			}     
	}
	
	public static void singleThread() {
		
		Observable<Integer> integerObservable = Observable.create(source -> {
			System.out.println("In subscribe");
			source.onNext(1);
			source.onNext(2);
			source.onNext(3);
			source.onComplete();
		});
		System.out.println("Created Observable");

		integerObservable.subscribeOn(Schedulers.newThread()).subscribe(i -> System.out.println("In onNext(): " + i));
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}

		System.out.println("Finished");
	}

}
