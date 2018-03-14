package ru.shok.javacore2017.supermarket_simulator;

import org.junit.jupiter.api.Test;
import ru.shok.javacore2017.SupermarketSimulator;

import static org.junit.jupiter.api.Assertions.fail;

class SupermarketSimulatorTest {
	@Test
	void doNotThrowException() {
		try {
			SupermarketSimulator.main(new String[]{});
		} catch (Exception exception) {
			fail("SupermarketSimulator shouldn't throw exception");
		}
	}
}
