package ru.shok.javacore2017.model.cash_desk;

import ru.shok.javacore2017.model.customer.Customer;
import ru.shok.javacore2017.model.customer.CustomerInterface;
import ru.shok.javacore2017.model.customer.CustomerType;
import ru.shok.javacore2017.model.product.Product;
import ru.shok.javacore2017.model.product.ProductInterface;
import ru.shok.javacore2017.payment.Bill;
import ru.shok.javacore2017.payment.PaymentMethod;
import ru.shok.javacore2017.report.ReporterInterface;
import ru.shok.javacore2017.supermarket.Supermarket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class CashDesk implements CashDeskInterface {
	private final Supermarket supermarket;
	private final List<CustomerInterface> queue = new ArrayList<>();
	private ReporterInterface reporter;

	public CashDesk(Supermarket supermarket) {
		this.supermarket = supermarket;
	}

	public int getQueueSize() {
		return queue.size();
	}

	public void setReporter(ReporterInterface value) {
		reporter = value;
	}

	public void addCustomerToQueue(CustomerInterface customer) {
		queue.add(customer);
		customer.setInQueue(true);
		System.out.printf("Customer %s at the cash desk\n", customer.getName());
	}

	public void processQueue(int customerCount) {
		forEachNCustomers(customerCount, (Iterator<CustomerInterface> customerIterator) -> {
			CustomerInterface customer = customerIterator.next();

			Bill bill = createBillForCustomer(customer);

			if (!bill.getProducts().isEmpty()) {
				System.out.printf("Amount to pay: %.2f", bill.calculateTotalAmount());
				bill.setDiscount(supermarket.getDiscount(customer.getType()));
				double totalAmount = bill.calculateTotalAmount();
				if (bill.getDiscount().getPercentage() != 0) {
					System.out.printf(", with discount: %.2f", totalAmount);
				}
				System.out.println();

				double totalBonuses = bill.calculateTotalBonuses();
				PaymentMethod paymentMethod = customer.getDesiredPaymentMethod(totalAmount);
				System.out.print(customer.getName());
				if (!customer.pay(bill)) {
					System.out.printf(" can't pay (%.2f)\n", totalAmount);
					bill.getProducts().values().forEach((ProductInterface product) ->
							supermarket.giveProductBack(product, customer));
				} else {
					System.out.printf(" paid (%.2f)", totalAmount);
					switch (paymentMethod) {
						case Cash:
							System.out.printf(" by cash and got %.2f bonuses\n", totalBonuses);
							break;
						case Card:
							System.out.printf(" by card and got %.2f bonuses\n", totalBonuses);
							break;
						case Bonuses:
							System.out.println(" by bonuses");
							break;
					}
					if (reporter != null) {
						bill.getProducts().values().forEach((ProductInterface product) -> reporter.addProductSelling(product));
					}
				}
			}

			customerIterator.remove();
			supermarket.removeCustomer(null, customer);
		});
	}

	private void forEachNCustomers(int customerCount, Consumer<Iterator<CustomerInterface>> action) {
		Iterator<CustomerInterface> customerIterator = queue.iterator();
		while (customerIterator.hasNext()) {
			if (customerCount == 0) {
				break;
			}
			action.accept(customerIterator);
			--customerCount;
		}
	}

	private Bill createBillForCustomer(CustomerInterface customer) {
		Bill bill = new Bill();
		customer.getBucket().forEachProduct((Iterator<ProductInterface> productIterator) -> {
			ProductInterface product = customer.getBucket().remove(productIterator);
			if (product.isForAdult() && customer.getType() == CustomerType.Child) {
				System.out.printf("Attempt of  %s buying %s prevented\n", customer.getName(), product.getName());
				supermarket.giveProductBack(product, customer);
			} else {
				bill.add(product);
			}
		});

		return bill;
	}
}
