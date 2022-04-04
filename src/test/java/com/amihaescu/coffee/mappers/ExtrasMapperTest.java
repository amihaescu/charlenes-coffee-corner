package com.amihaescu.coffee.mappers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExtrasMapperTest {

    private final ExtrasMapper extrasMapper = new ExtrasMapperImpl();

    @Test
    void should_successfully_map_line_to_extra() {
        var extras = extrasMapper.toExtra("1,2,3;11;0.30;CHF;Extra milk");
        assertEquals( 0.30f, extras.getPrice().getAmount());
        assertEquals( "CHF", extras.getPrice().getCurrency());
        assertTrue(extras.getForProduct().containsAll(List.of(1,2,3)));
        assertEquals("Extra milk", extras.getName());
    }

    @Test
    void should_throw_illegal_argument_exception() {
        assertThrows(IllegalArgumentException.class, () -> extrasMapper.toExtra("1,2,3;11;0.30;CHF"));
    }
}
