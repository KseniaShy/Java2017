package ru.shok.javacore2017.payment;

import ru.shok.javacore2017.model.ProductContainer.ProductContainer;
import ru.shok.javacore2017.model.product.ProductInterface;

public class ProductAdder extends ProductContainer {
	public double calculateTotalAmount() {
		double result = 0;
		for (ProductInterface product : getProducts().values()) {
			result += product.getAmount() * product.getPrice();
		}
		return result;
	}

	public double calculateTotalBonuses() {
		double result = 0;
		for (ProductInterface product : getProducts().values()) {
			result += product.getAmount() * product.getBonus();
		}
		return result;
	}
}
