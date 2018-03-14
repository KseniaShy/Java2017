package ru.shok.javacore2017.model.ProductContainer;

import ru.shok.javacore2017.model.product.ProductInterface;

interface ProductContainerInterface extends Removable, Clearable {
	void add(ProductInterface product);
	boolean isEmpty();
}
