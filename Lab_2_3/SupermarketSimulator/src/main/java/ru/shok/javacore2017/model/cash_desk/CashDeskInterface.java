package ru.shok.javacore2017.model.cash_desk;

import ru.shok.javacore2017.model.customer.CustomerInterface;

public interface CashDeskInterface extends ReportingCashDesk {
	int getQueueSize();
	void addCustomerToQueue(CustomerInterface customer);
	void processQueue(int customerCount);
}
