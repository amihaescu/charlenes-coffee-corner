package com.amihaescu.coffee.display;

import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Order;
import com.amihaescu.coffee.model.Product;

import java.util.Map;
import java.util.stream.Collectors;

public class DisplayHelper {
    private static final String MENU_TITLE = "Welcome to Charlene's Coffee Corner";
    private static final String CHOOSE_ITEM = "Please choose item:";
    private static final String PAY_ORDER = "0 - Pay order";

    public String displayExtras(Map<Integer, Extra> extras) {
        String collect = extras.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(Extra::toString)
                .collect(Collectors.joining("\n"));
        return String.format("%s\n%s",collect , CHOOSE_ITEM);
    }

    public String displayMenu(Map<Integer, Product> products, Order order) {
        var productsString = products.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(Product::toString)
                .collect(Collectors.joining("\n"));
        if (order.containsItems()) {
            return String.format("%s\n%s\n%s\n%s", MENU_TITLE, productsString, PAY_ORDER, CHOOSE_ITEM);
        } else {
            return String.format("%s\n%s\n%s", MENU_TITLE, productsString, CHOOSE_ITEM);
        }
    }

    public boolean isEligibleForExtra(Map<Integer, Extra> extras, int option) {
        return extras.values().stream().flatMap(extra -> extra.getForProduct().stream()).collect(Collectors.toSet()).contains(option);
    }
}
