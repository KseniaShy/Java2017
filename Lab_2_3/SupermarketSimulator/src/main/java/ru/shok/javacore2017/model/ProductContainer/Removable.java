package ru.shok.javacore2017.model.ProductContainer;

import ru.shok.javacore2017.model.product.ProductInterface;

import java.util.Iterator;

interface Removable {
	ProductInterface remove(ProductInterface product);
	ProductInterface remove(Iterator<ProductInterface> productIterator);
}
