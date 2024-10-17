package com.example.sysark_lab2;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Warehouse {
    private final List<Product> products = new CopyOnWriteArrayList<>();
    private int nextId = 1;

    public Warehouse() {
        //addProduct(new Product(nextId++, "tv", Category.TV, 5, LocalDate.now(), LocalDate.now()));
    }

    public void addProduct(Product product) {
        Product newProduct = new Product(
                nextId++,
                product.name(),
                product.category(),
                product.rating(),
                LocalDate.now(),
                null);

        for (Product p : products) {
            if (p.id() == newProduct.id()) {
                throw new IllegalArgumentException("Duplicate product");
            }
        }
        products.add(newProduct);
    }

    public void modifyProduct(int id, String newName, Category newCategory, int newRating) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.id() == id) {
                products.set(i, new Product(product.id(), newName, newCategory, newRating,
                        product.createdDate(), LocalDate.now()));
                return;
            }
        }
    }

    public List<Product> getAllProducts() {
        return List.copyOf(products);
    }

    public Optional<Product> getProductById(int id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst();
    }

    public List<Product> getProductByCategory(Category category) {
        return List.copyOf(
                products.stream()
                        .filter(product -> product.category() == category)
                        .sorted((product1, product2) -> product1.name().compareTo(product2.name()))
                        .collect(Collectors.toList())
        );
    }

    public List<Product> getProductsByDate(LocalDate date) {
        return products.stream()
                .filter(product -> product.createdDate().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Product> getModifiedProducts() {
        return products.stream()
                .filter(product -> !product.createdDate().equals(product.modifiedDate()))
                .collect(Collectors.toList());

    }

}
