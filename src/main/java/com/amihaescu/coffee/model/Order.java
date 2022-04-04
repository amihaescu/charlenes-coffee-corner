package com.amihaescu.coffee.model;

import java.util.*;
import java.util.stream.Collectors;

public class Order {

    private static final String FINAL_ORDER = "Final order";
    private static final String CURRENT_ORDER = "Current order";
    private final Map<Product, Integer> products;
    private float total;
    private boolean containsBeverage = false;
    private boolean containsSnack = false;
    private boolean freeExtraCollected = false;

    public Order() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
        total += product.getPrice().getAmount();
        if ("B".equalsIgnoreCase(product.getType()) && !containsBeverage) containsBeverage = true;
        if ("S".equalsIgnoreCase(product.getType()) && !containsSnack) containsSnack = true;
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

    public boolean isEligibleForFreeExtra() {
        return containsSnack && containsBeverage && !freeExtraCollected;
    }

    public float getTotal() {
        return total;
    }

    public void addFreeExtra(Extra extra){
        if (isEligibleForFreeExtra() && !freeExtraCollected) {
            products.merge(extra.toFree(), 1, Integer::sum);
            freeExtraCollected = true;
        }
    }


}
