package ru.shok.javacore2017.model.product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTypeTest {

	@Test
	void createFromInteger() {
		assertEquals(ProductType.Packed, ProductType.createFromInteger(1));
		assertEquals(ProductType.Bulk, ProductType.createFromInteger(2));
	}
}
