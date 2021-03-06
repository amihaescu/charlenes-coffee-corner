package com.amihaescu.coffee.model;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void order_should_be_eligible_for_free_extra() {
        var order = new Order();
        order.addProduct(new Product(1, new Price(1, "CHF"), "beverage", "B"));
        order.addProduct(new Product(1, new Price(1, "CHF"), "snack", "S"));
        assertTrue(order.isEligibleForFreeExtra());
    }

    @Test
    void order_should_not_be_eligible_for_free_extra() {
        var order = new Order();
        assertFalse(order.isEligibleForFreeExtra());

        order = new Order();
        order.addProduct(new Product(1, new Price(1, "CHF"), "beverage", "B"));
        assertFalse(order.isEligibleForFreeExtra());

        order = new Order();
        order.addProduct(new Product(1, new Price(1, "CHF"), "snack", "S"));
        assertFalse(order.isEligibleForFreeExtra());
    }

    @Test
    void should_be_able_to_add_free_extra() {
        var foamExtra = new Extra(1, List.of(1,2), new Price(1, "CHF"), "Foam");
        var order = new Order();
        order.addProduct(new Product(1, new Price(1, "CHF"), "beverage", "B"));
        order.addProduct(new Product(1, new Price(1, "CHF"), "snack", "S"));
        order.addFreeExtra(foamExtra);
        assertEquals(2, order.getTotal());
    }
}