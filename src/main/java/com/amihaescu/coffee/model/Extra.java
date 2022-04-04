package com.amihaescu.coffee.model;

import java.util.ArrayList;
import java.util.List;

public final class Extra extends Product {

    private final List<Integer> forProduct;

    public Extra(int menuItem, List<Integer> forProduct, Price price, String name) {
        super(menuItem, price, name);
        this.forProduct = forProduct;
    }

    public Extra() {
        super();
        this.forProduct = new ArrayList<>();
    }

    public List<Integer> getForProduct() {
        return forProduct;
    }
}
