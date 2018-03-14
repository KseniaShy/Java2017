package ru.shok.javacore2017.model.ProductContainer;

import ru.shok.javacore2017.model.product.ProductInterface;

import java.util.HashMap;
import java.util.Iterator;

public class ProductContainer implements ProductContainerInterface {
	private final HashMap<Integer, ProductInterface> products = new HashMap<>();
	public HashMap<Integer, ProductInterface> getProducts() {
		return products;
	}

	public void add(ProductInterface product) {
		if (products.containsKey(product.getId())) {
			ProductInterface productInContainer = products.get(product.getId());
			productInContainer.setAmount(productInContainer.getAmount() + product.getAmount());
		} else {
			products.put(product.getId(), product);
		}
	}

	public boolean isEmpty() {
		return products.isEmpty();
	}

	public ProductInterface remove(ProductInterface product) {
		return products.remove(product.getId());
	}

	public ProductInterface remove(Iterator<ProductInterface> productIterator) {
		ProductInterface result = productIterator.next();
		productIterator.remove();
		return result;
	}

	public void clear() {
		products.clear();
	}
}
