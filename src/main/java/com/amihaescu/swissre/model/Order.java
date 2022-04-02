package com.amihaescu.swissre.model;

import java.util.*;

public class Order {

    private final Map<Product, Integer> products;
    private float total;

    public Order() {
        products = new HashMap<>();
    }

    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
        total += product.getPrice().getAmount();
    }

    public void display(boolean isFinal) {
        if (!products.isEmpty()) {
            if (isFinal) {
                System.out.println("Final order");
            } else {
                System.out.println("Current order");
            }

            products.forEach((product, quantity) ->
                    System.out.println(quantity + " x " + product.getName() + " " + product.getPrice().getAmount() * quantity + " " + product.getPrice().getCurrency()));
            System.out.println("Total: "+ total + "" + products.entrySet().stream().findAny().get().getKey().getPrice().getCurrency());

            System.out.println();
        }
    }

    public boolean containsItems() {
        return !products.isEmpty();
    }


}
