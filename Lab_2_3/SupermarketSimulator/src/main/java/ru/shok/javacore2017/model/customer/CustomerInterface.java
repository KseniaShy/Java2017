package ru.shok.javacore2017.model.customer;

import ru.shok.javacore2017.model.Identified;
import ru.shok.javacore2017.model.Named;
import ru.shok.javacore2017.model.bucket.Bucket;
import ru.shok.javacore2017.payment.Bill;
import ru.shok.javacore2017.payment.PaymentMethod;

public interface CustomerInterface extends Identified, Named {
	void setId(int value);
	CustomerType getType();
	Bucket getBucket();
	boolean isInQueue();
	void setInQueue(boolean value);
	boolean pay(Bill bill);
	double getCash();
	double getCardCash();
	double getBonusCount();
	PaymentMethod getDesiredPaymentMethod(double totalPaymentAmount);
}
