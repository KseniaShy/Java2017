package ru.shok.javacore2017;

import ru.shok.javacore2017.model.customer.CustomerType;
import ru.shok.javacore2017.supermarket.Supermarket;
import ru.shok.javacore2017.supermarket.SupermarketWork;

public class SupermarketSimulator {
	private static final int WORKING_TIME_MINUTES = 30;
	private static final int RETIRED_DISCOUNT_PERCENT = 15;

	public static void main(String[] args) {
		Supermarket supermarket = new Supermarket(WORKING_TIME_MINUTES);
		supermarket.addDiscount(CustomerType.Retired, RETIRED_DISCOUNT_PERCENT);
		supermarket.addCashDesks(2);
		supermarket.work(new SupermarketWork());
	}
}
