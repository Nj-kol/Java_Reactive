package com.njkol.rxjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.njkol.rxjava.dto.Car;
import com.njkol.rxjava.dto.User;
import com.njkol.rxjava.multhithreaded.DemoThrottlingAndBuffering;
import com.njkol.rxjava.multhithreaded.SchedulersDemos;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaDemos {

	static NetworkClient networkClient = new NetworkClient(); 

  
    public static void main(String[] args) {
    	// demo2();
    	// SchedulersDemos.singleThread();
    	// SchedulersDemos.demoOnObserve();
    	// SchedulersDemos.demoFlatMap();
    	 
    	// DemoThrottlingAndBuffering.demoThrottling();
    	 DemoThrottlingAndBuffering.demoBuffer();
    }
    
    public static Observable<Car> getBestSellingCarsObservable() { 
        return Observable.fromIterable(networkClient.fetchBestSellingCars());
	}
   
    private static void demo1 () {
    	
        Observable<Car> carsObservable = getBestSellingCarsObservable();
        
		System.out.println("Year" + " " + "Make" + "    " + "Model");
        carsObservable.subscribeOn(Schedulers.io())
			.filter(car -> car.type == Car.Type.ALL_ELECTRIC)
			.filter(car -> car.price < 90000)
 			.map(car -> car.year + " " + car.make + "   " + car.model)
 			.distinct()
 			.take(5)
 			.observeOn(Schedulers.io())
 			.subscribe(ca -> System.out.println(ca));
 		try{
			Thread.sleep(100);
		}
		 catch (InterruptedException e) {
		}
    	
    }
    
    
    public static Observable<User> getUsersWithABlog(List<User> users) {
        return Observable.fromIterable(users)
                .filter(user -> user.hasBlog())
                .subscribeOn(Schedulers.io());
    }
    
    private static void demo2 () {
    	 User user1, user2, user3;
         user1 = new User("Jack", true, 121);
         user2 = new User("Chris", true, 122);
         user3 = new User("Max", false, 123);
         List<User> users = new ArrayList<>();
         users.add(user1);
         users.add(user2);
         users.add(user3);

         Observable<User> userBlog = getUsersWithABlog(users);
         userBlog.subscribe (user -> System.out.println("Username: " + user.username + " " + "\n" + "User ID: " + user.id + "\n"));
         try {
 			Thread.sleep(10);
 		} catch (InterruptedException e) {
 	    }
    }
}
