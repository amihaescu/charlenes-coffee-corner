package com.amihaescu.coffee.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void should_successfully_print_non_final_order() {
        var order = new Order();
        order.addProduct(new Product());
        var expected = "\nCurrent order\n1 x Default name 1.0 CHF\nTotal: 1.0 CHF\n";
        assertEquals(expected, order.toString(false));
    }

    @Test
    void should_successfully_print_final_order() {
        var order = new Order();
        order.addProduct(new Product());
        var expected = "\nFinal order\n1 x Default name 1.0 CHF\nTotal: 1.0 CHF\n";
        assertEquals(expected, order.toString(true));
    }

    @Test
    void order_contains_items() {
        var order = new Order();
        order.addProduct(new Product());
        assertTrue(order.containsItems());
    }

    @Test
    void order_does_not_contain_items() {
        var order = new Order();
        assertFalse(order.containsItems());
    }
}