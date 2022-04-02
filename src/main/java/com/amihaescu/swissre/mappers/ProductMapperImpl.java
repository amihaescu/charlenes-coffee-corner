package com.amihaescu.swissre.mappers;

import com.amihaescu.swissre.model.Price;
import com.amihaescu.swissre.model.Product;

public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(String input) {
        String[] split = input.split(";");
        if (split.length != 4) throw new IllegalArgumentException();
        var menuItem = Integer.parseInt(split[0]);
        var price = new Price(Float.parseFloat(split[1]), split[2]);
        var name = split[3];
        return new Product(menuItem, price, name);
    }
}
