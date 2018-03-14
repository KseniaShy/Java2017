package ru.shok.javacore2017.report;

import ru.shok.javacore2017.model.product.Product;
import ru.shok.javacore2017.model.product.ProductInterface;
import ru.shok.javacore2017.payment.ProductAdder;

public class Reporter implements ReporterInterface {
	private final ProductAdder productAdder = new ProductAdder();

	public void addProductSelling(ProductInterface product) {
		productAdder.add(product);
	}

	public void printReport() {
		System.out.println("Report:");
		System.out.println("---------------------------------------");
		System.out.printf("| %-15s | %-10s | %-7s |%n", "Product", "Amount", "Earning");
		System.out.println("---------------------------------------");
		productAdder.getProducts().values().forEach((ProductInterface product) ->
				System.out.printf("| %-15s | %-10d | %-7.2f |%n",
						product.getName(), product.getAmount(), product.getAmount() * product.getPrice())
		);
		System.out.println("---------------------------------------");
		System.out.printf("Total earning: %.2f\n", productAdder.calculateTotalAmount());
	}
}
