package com.amihaescu.swissre.model;

import java.util.List;

public final class Extra extends Product {

    private final List<Integer> forProduct;

    public Extra(int menuItem, List<Integer> forProduct, Price price, String name) {
        super(menuItem, price, name);
        this.forProduct = forProduct;
    }

    public List<Integer> getForProduct() {
        return forProduct;
    }
}
