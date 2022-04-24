package com.njkol.rxjava.multhithreaded;

import io.reactivex.Observable;
import io.reactivex.Observer;
import java.util.concurrent.TimeUnit;
import io.reactivex.disposables.Disposable;

public class DemoThrottlingAndBuffering {

	public static void demoThrottling() {

		Disposable disposable1;
		Disposable disposable2;
		Disposable disposable3;

		Observable<Long> observable1 = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> observable2 = Observable.interval(1, TimeUnit.SECONDS);
		Observable<Long> observable3 = Observable.interval(1, TimeUnit.SECONDS);

		// Sample
		System.out.println("Sample");

		disposable1 = observable1.sample(3, TimeUnit.SECONDS).subscribe(System.out::println);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		disposable1.dispose();

		// ThrottleFirst
		System.out.println("ThrottleFirst");
		disposable2 = observable2.throttleFirst(3, TimeUnit.SECONDS).subscribe(System.out::println);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		disposable2.dispose();

		// ThrottleLast
		System.out.println("ThrottleLast");
		disposable3 = observable3.throttleLast(3, TimeUnit.SECONDS).subscribe(System.out::println);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		disposable3.dispose();
	}

	public static void demoBuffer() {

		// Buffer
		System.out.println("Buffer");
		Observable.range(1, 20).buffer(5).subscribe(System.out::println);
		
		// Window
		System.out.println("Window");
		Observable.range(1, 20).window(5)
				.flatMapSingle(obs -> obs.reduce("", (t, n) -> t + (t.equals("") ? "" : ",") + n))
				.subscribe(System.out::println);
	}
}
