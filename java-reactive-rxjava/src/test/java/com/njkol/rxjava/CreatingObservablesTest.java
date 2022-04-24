package com.njkol.rxjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CreatingObservablesTest {

	CreatingObservables co = new CreatingObservables();
	
	@Test
	void test() {
		
		co.createObservableWithJust();
		co.createObservableFromIterable();
		co.createObservableUsingCreate();
	}

}
