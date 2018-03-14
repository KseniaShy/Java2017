package ru.shok.javacore2017.supermarket;

import ru.shok.javacore2017.model.customer.Customer;
import ru.shok.javacore2017.model.customer.CustomerInterface;
import ru.shok.javacore2017.model.customer.CustomerType;
import ru.shok.javacore2017.model.product.ProductInterface;

import java.util.Iterator;

import static ru.shok.javacore2017.random_helper.RandomHelper.getRandomNumber;

public class SupermarketWork implements SupermarketWorkInterface {
	@Override
	public void onEachTimeUnit(Supermarket supermarket) {
		addCustomersToSupermarket(supermarket);
		supermarket.forEachCustomer((Iterator<CustomerInterface> customerIterator) -> {
			CustomerInterface customer = customerIterator.next();

			addProductsToCustomer(supermarket, customer);
			tryToExitOrGiveSomeProductsBack(supermarket, customer, customerIterator);
			if (!customer.isInQueue() && !customer.getBucket().isEmpty()) {
				supermarket.getOptimalCashDesk().addCustomerToQueue(customer);
			}
		});
		supermarket.processCashDesks();
	}

	@Override
	public void onFinished(Supermarket supermarket) {
		supermarket.forEachCustomer((Iterator<CustomerInterface> customerIterator) -> {
			CustomerInterface customer = customerIterator.next();
			customer.getBucket().forEachProduct((Iterator<ProductInterface> productIterator) -> {
				ProductInterface product = productIterator.next();
				customer.getBucket().remove(productIterator);
				supermarket.giveProductBack(product, customer);
			});
			supermarket.removeCustomer(customerIterator, customer);
		});
		supermarket.printReport();
	}

	private void addCustomersToSupermarket(Supermarket supermarket) {
		for (int i = 0; i < getRandomNumber(0, Supermarket.TIME_UNIT_MINUTES); ++i) {
			supermarket.addCustomer(new Customer(CustomerType.getRandom(), getRandomNumber(0, 1000), getRandomNumber(0, 10000), getRandomNumber(0, 1000)));
		}
	}

	private void addProductsToCustomer(Supermarket supermarket, CustomerInterface customer) {
		int wantedProductCount = getRandomNumber(0, 6);
		for (int i = 0; i < wantedProductCount; ++i) {
			int randomProductIndex = getRandomNumber(0, supermarket.getProducts().size() - 1);
			ProductInterface randomProduct = supermarket.getProducts().get(randomProductIndex);
			int productMaxAmount = randomProduct.getAmount();
			if (productMaxAmount == 0) {
				continue;
			}
			int productAmount = randomProduct.getRandomProductAmount(productMaxAmount / 5) + 1;
			ProductInterface wantedProduct = supermarket.getProduct(customer, randomProduct.getId(), productAmount);
			if (wantedProduct != null) {
				customer.getBucket().add(wantedProduct);
			}
		}
	}

	private void tryToExitOrGiveSomeProductsBack(
			Supermarket supermarket, CustomerInterface customer, Iterator<CustomerInterface> customerIterator
	) {
		if (getRandomNumber(0, 1) == 0) {
			switch (getRandomNumber(0, 6)) {
				case 0:
					supermarket.removeCustomer(customerIterator, customer);
					break;
				case 1:
					customer.getBucket().forEachProduct((Iterator<ProductInterface> productIterator) -> {
						ProductInterface product = customer.getBucket().remove(productIterator);
						supermarket.giveProductBack(product, customer);
					});
					supermarket.removeCustomer(customerIterator, customer);
					break;
				default:
					customer.getBucket().forEachProduct((Iterator<ProductInterface> productIterator) -> {
						if (getRandomNumber(0, 4) == 0) {
							ProductInterface product = customer.getBucket().remove(productIterator);
							supermarket.giveProductBack(product, customer);
						}
					});
			}
		}
	}
}
