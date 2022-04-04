package com.amihaescu.coffee.mappers;

import com.amihaescu.coffee.model.Price;
import com.amihaescu.coffee.model.Product;

public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(String input) {
        String[] split = input.split(";");
        if (split.length != 5) throw new IllegalArgumentException();
        var menuItem = Integer.parseInt(split[0]);
        var price = new Price(Float.parseFloat(split[1]), split[2]);
        var name = split[3];
        var type = split[4];
        return new Product(menuItem, price, name, type);
    }
}
