package ru.shok.javacore2017.model.product;

import ru.shok.javacore2017.model.Identified;
import ru.shok.javacore2017.model.Named;

public interface ProductInterface extends Identified, Named {
	ProductType getType();
	int getAmount();
	void setAmount(int value);
	double getBonus();
	boolean isForAdult();
	int getRandomProductAmount(int maxProductAmount);
	double getPrice();
}
