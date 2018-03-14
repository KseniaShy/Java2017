package ru.shok.javacore2017.model.bucket;

import ru.shok.javacore2017.model.ProductContainer.ProductContainer;
import ru.shok.javacore2017.model.product.ProductInterface;

import java.util.Iterator;
import java.util.function.Consumer;

public class Bucket extends ProductContainer {
	public void forEachProduct(Consumer<Iterator<ProductInterface>> action) {
		Iterator<ProductInterface> productIterator = getProducts().values().iterator();
		while (productIterator.hasNext()) {
			action.accept(productIterator);
		}
	}
}
