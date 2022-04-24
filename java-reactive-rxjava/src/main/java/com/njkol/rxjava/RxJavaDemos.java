package com.njkol.rxjava;

import java.util.Arrays;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;

public class RxJavaDemos {

	public static void main(String args[]) {

		Observable.range(1, 1_000_000).toFlowable(BackpressureStrategy.DROP);
	}

}
