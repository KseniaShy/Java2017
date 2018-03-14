package ru.shok.javacore2017.model.bucket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shok.javacore2017.model.product.Product;
import ru.shok.javacore2017.model.product.ProductInterface;
import ru.shok.javacore2017.model.product.ProductType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BucketTest {
	private Bucket bucket;

	@BeforeEach
	void setUp() {
		bucket = new Bucket();
	}

	@Test
	void forEachProduct() {
		bucket.add(getProduct(1));
		bucket.add(getProduct(2));
		bucket.add(getProduct(3));
		List<ProductInterface> products = new ArrayList<>();
		bucket.forEachProduct((Iterator<ProductInterface> productIterator) -> products.add(productIterator.next()));
		for (int i = 0; i < products.size(); ++i) {
			ProductInterface product = products.get(i);
			ProductInterface expectedProduct = getProduct(i + 1);
			assertTrue(areProductsEqual(product, expectedProduct));
		}
	}

	private ProductInterface getProduct(int id) {
		return new Product(id, "Test", 2, 3, ProductType.Packed, 4, true);
	}

	private boolean areProductsEqual(ProductInterface lhs, ProductInterface rhs) {
		return lhs.getId() == rhs.getId()
				&& lhs.getName().equals(rhs.getName())
				&& lhs.getPrice() == rhs.getPrice()
				&& lhs.getAmount() == rhs.getAmount()
				&& lhs.getType() == rhs.getType()
				&& lhs.getBonus() == rhs.getBonus()
				&& lhs.isForAdult() == rhs.isForAdult();
	}
}
