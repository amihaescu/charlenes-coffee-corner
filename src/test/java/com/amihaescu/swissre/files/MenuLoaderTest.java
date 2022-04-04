package com.amihaescu.swissre.files;

import com.amihaescu.swissre.mappers.ExtrasMapperImpl;
import com.amihaescu.swissre.mappers.ProductMapperImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuLoaderTest {

    private final FileLoader fileLoader = new FileLoader(new ExtrasMapperImpl(), new ProductMapperImpl());

    @Test
    void should_load_products() {
        var result = fileLoader.loadProducts();
        assertEquals(5, result.entrySet().size());
    }

    @Test
    void should_load_extras() {
        var result = fileLoader.loadExtras();
        assertEquals(3, result.entrySet().size());
    }
}
