package com.amihaescu.swissre.model;

import java.util.*;
import java.util.stream.Collectors;

public class Order {

    private static final String FINAL_ORDER = "Final order";
    private static final String CURRENT_ORDER = "Current order";
    private final Map<Product, Integer> products;
    private float total;

    public Order() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
        total += product.getPrice().getAmount();
    }

    public String toString(boolean isFinal) {
        var productsString = products.entrySet().stream()
                .map(entry -> {
                    var quantity = entry.getValue();
                    var product = entry.getKey();
                    return String.format("%s x %s %s %s", quantity, product.getName(), product.getPrice().getAmount() * quantity, product.getPrice().getCurrency());
                })
                .collect(Collectors.joining("\n"));
        if (!products.isEmpty()) {
            var totalString = String.format("Total: %s %s",
                    total , products.entrySet().stream().findAny().get().getKey().getPrice().getCurrency());
            if (isFinal) {
                return String.format("\n%s\n%s\n%s\n", FINAL_ORDER, productsString, totalString);
            } else {
                return String.format("\n%s\n%s\n%s\n", CURRENT_ORDER, productsString, totalString);
            }
        }
        return "";
    }

    public boolean containsItems() {
        return !products.isEmpty();
    }


}
