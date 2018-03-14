package ru.shok.javacore2017.supermarket;

import ru.shok.javacore2017.model.product.Product;
import ru.shok.javacore2017.model.product.ProductInterface;
import ru.shok.javacore2017.model.product.ProductType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Warehouse {
	private final HashMap<Integer, ProductInterface> products = new HashMap<>() {{
		put(1, new Product(1, "Bread", 20, 100, ProductType.Packed, 10, false));
		put(2, new Product(2, "Milk", 50, 100, ProductType.Packed, 10, false));
		put(3, new Product(3, "Buckwheat", 0.06, 10000, ProductType.Bulk, 10, false));
		put(4, new Product(4, "Rice groats", 0.05, 15000, ProductType.Bulk, 10, false));
		put(5, new Product(5, "Curd", 200, 50, ProductType.Packed, 15, false));
		put(6, new Product(6, "Kvass", 100, 60, ProductType.Packed, 20, true));
		put(7, new Product(7, "Crisp", 100, 50, ProductType.Packed, 20, true));
	}};

	private static Warehouse instance = null;
	private Warehouse() { }

	public static Warehouse getInstance() {
		if (instance == null) {
			instance = new Warehouse();
		}
		return instance;
	}

	public List<ProductInterface> getProducts() {
		return new ArrayList<>(products.values());
	}

	public ProductInterface fetchProduct(int productId, int productAmount) {
		if (!products.containsKey(productId) || products.get(productId).getAmount() - productAmount < 0) {
			return null;
		}
		ProductInterface product = products.get(productId);
		product.setAmount(product.getAmount() - productAmount);
		return new Product(
				product.getId(),
				product.getName(),
				product.getPrice(),
				productAmount,
				product.getType(),
				product.getBonus(),
				product.isForAdult()
		);
	}

	public void giveProductBack(ProductInterface productToGiveBack) {
		ProductInterface product = products.get(productToGiveBack.getId());
		product.setAmount(product.getAmount() + productToGiveBack.getAmount());
	}
}
