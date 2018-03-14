package ru.shok.javacore2017.report;

import ru.shok.javacore2017.model.product.ProductInterface;

public interface ReporterInterface {
	void addProductSelling(ProductInterface product);
	void printReport();
}
