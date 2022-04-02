package com.amihaescu.swissre.mappers;

import com.amihaescu.swissre.model.Extra;
import com.amihaescu.swissre.model.Price;

import java.util.stream.Stream;

public class ExtrasMapperImpl implements ExtrasMapper {

    @Override
    public Extra toExtra(String input) {
        String[] split = input.split(";");
        if (split.length != 5) throw new IllegalArgumentException();
        var forMainProduct = Stream.of(split[0].split(",")).map(Integer::valueOf).toList();
        var menuItem = Integer.parseInt(split[1]);
        var price = new Price(Float.parseFloat(split[2]), split[3]);
        var name = split[4];
        return new Extra(menuItem, forMainProduct, price, name);
    }
}
