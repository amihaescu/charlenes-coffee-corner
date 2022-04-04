package com.amihaescu.coffee.display;

import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Order;
import com.amihaescu.coffee.model.Price;
import com.amihaescu.coffee.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DisplayHelperTest {

    private final DisplayHelper displayHelper = new DisplayHelper();

    @Test
    void should_display_extras() {
        var actual = displayHelper.displayExtras(Map.of(1, new Extra(1, List.of(1,2), new Price(1, "CHF"), "Foam")));
        var expected = "1 - Foam - 1.0CHF\nPlease choose item:";
        assertEquals(expected, actual);
    }

    @Test
    void should_display_products_without_pay_option() {
        var actual = displayHelper.displayMenu(Map.of(1, new Product(1, new Price(1, "CHF"), "Coffee")), new Order());
        var expected = "Welcome to Charlene's Coffee Corner\n1 - Coffee - 1.0CHF\nPlease choose item:";
        assertEquals(expected, actual);
    }

    @Test
    void should_display_products_with_pay_option() {
        Product coffee = new Product(1, new Price(1, "CHF"), "Coffee");
        Order order = new Order();
        order.addProduct(coffee);
        var actual = displayHelper.displayMenu(Map.of(1, coffee), order);
        var expected = "Welcome to Charlene's Coffee Corner\n1 - Coffee - 1.0CHF\n0 - Pay order\nPlease choose item:";
        assertEquals(expected, actual);
    }

    @Test
    void should_be_eligible_for_extras() {
        var foamExtra = new Extra(1, List.of(1,2), new Price(1, "CHF"), "Foam");
        var almondExtra = new Extra(2, List.of(1,2), new Price(1, "CHF"), "Almond Milk");
        var extras = Map.of(1, foamExtra, 2, almondExtra);
        assertTrue(displayHelper.isEligibleForExtra(extras, 1));
    }

    @Test
    void should_not_be_eligible_for_extraas() {
        var foamExtra = new Extra(1, List.of(1,2), new Price(1, "CHF"), "Foam");
        var almondExtra = new Extra(2, List.of(1,2), new Price(1, "CHF"), "Almond Milk");
        var extras = Map.of(1, foamExtra, 2, almondExtra);
        assertFalse(displayHelper.isEligibleForExtra(extras, 3));
    }
}