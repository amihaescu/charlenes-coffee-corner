package com.amihaescu.swissre.model;

public final class Extra {

    private final int menuItem;
    private final int forProduct;
    private final Price price;
    private final String name;

    public Extra(int menuItem, int forProduct, Price price, String name) {
        this.menuItem = menuItem;
        this.forProduct = forProduct;
        this.price = price;
        this.name = name;
    }

    public int getMenuItem() {
        return menuItem;
    }

    public int getForProduct() {
        return forProduct;
    }

    public Price getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return menuItem + " - " + name + " - " + price;
    }

}
