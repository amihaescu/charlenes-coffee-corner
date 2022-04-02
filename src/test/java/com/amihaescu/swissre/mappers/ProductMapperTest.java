package com.amihaescu.swissre.mappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void should_successfully_map_line_to_extra() {
        var extras = productMapper.toProduct("11;0.30;CHF;Extra milk");
        assertEquals( 0.30f, extras.getPrice().getAmount());
        assertEquals( "CHF", extras.getPrice().getCurrency());
        assertEquals("Extra milk", extras.getName());
    }

    @Test
    void should_throw_illegal_argument_exception() {
        assertThrows(IllegalArgumentException.class, () -> productMapper.toProduct("11;0.30;CHF"));
    }
}
