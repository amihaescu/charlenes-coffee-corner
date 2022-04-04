package com.amihaescu.coffee.model;

public class Product {

    private final int menuItem;
    private final Price price;
    private final String name;

    public Product() {
        this.menuItem = 0;
        this.price = new Price(1.0f, "CHF");
        this.name = "Default name";
    }

    public Product(int menuItem, Price price, String name) {
        this.menuItem = menuItem;
        this.price = price;
        this.name = name;
    }

    public int getMenuItem() {
        return menuItem;
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
