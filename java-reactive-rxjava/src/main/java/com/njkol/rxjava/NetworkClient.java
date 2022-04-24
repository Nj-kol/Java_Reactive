package com.njkol.rxjava;

import java.util.Random;

import com.njkol.rxjava.dto.Car;
import com.njkol.rxjava.dto.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class NetworkClient {

	Random random = new Random();

	List<Car> fetchBestSellingCars() {
		// perform blocking network call here but for the sake of the example,
		// mimic network latency by adding a random thread sleep and return
		// a new User object
		randomSleep();
		Car best1 = new Car(Car.Type.ALL_ELECTRIC, 80000, "2021", "Honda", "Civic");
		Car best2 = new Car(Car.Type.ALL_ELECTRIC, 70000, "2020", "Honda", "Civic");
		Car best3 = new Car(Car.Type.ALL_ELECTRIC, 80000, "2020", "Toyota", "Corolla");
		Car best4 = new Car(Car.Type.ALL_ELECTRIC, 70000, "2019", "Ford", "Fiesta");
		Car best5 = new Car(Car.Type.ALL_DIESEL, 50000, "2019", "Ford", "Fiesta");
		Car best6 = new Car(Car.Type.ALL_ELECTRIC, 10000, "2021", "Toyota", "Corolla");
		Car best7 = new Car(Car.Type.ALL_ELECTRIC, 60000, "2020", "Ford", "Ford Edge");
		Car best8 = new Car(Car.Type.ALL_ELECTRIC, 60000, "2020", "Toyota", "Camry");

		List<Car> bestSellingCars = new ArrayList<>();
		bestSellingCars.add(best1);
		bestSellingCars.add(best2);
		bestSellingCars.add(best3);
		bestSellingCars.add(best4);
		bestSellingCars.add(best5);
		bestSellingCars.add(best6);
		bestSellingCars.add(best7);
		bestSellingCars.add(best8);
		return bestSellingCars;
	}

	void randomSleep() {
		try {
			Thread.sleep(random.nextInt(3) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public User fetchUser(String username) {
		// perform blocking network call here but for the sake of the example,
		// mimic network latency by adding a random thread sleep and return
		// a new User object
		randomSleep();
		return new User(username);
	}

}