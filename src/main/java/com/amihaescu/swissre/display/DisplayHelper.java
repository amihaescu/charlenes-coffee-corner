package com.amihaescu.swissre.display;

import com.amihaescu.swissre.model.Extra;
import com.amihaescu.swissre.model.Order;
import com.amihaescu.swissre.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DisplayHelper {

    public void displayExtras(Map<Integer, Extra> extras) {
        extras.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).forEach(System.out::println);
        System.out.println("\nPlease choose item: ");
    }

    public void displayMenu(Map<Integer, Product> products, Order order) {
        System.out.println("Welcome to Charlene's Coffee Corner\n");
        products.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).forEach(System.out::println);
        if (order.containsItems()) {
            System.out.println("6 - Pay order");
        }
        System.out.println("\nPlease choose item: ");
    }

    public boolean isEligibleForExtra(Map<Integer, Extra> extras, int option) {
        return extras.values().stream().flatMap(extra -> extra.getForProduct().stream()).collect(Collectors.toSet()).contains(option);
    }

    public int processExtra(Map<Integer, Extra> extras, Scanner scanner) {
        var extraOption = scanner.nextInt();
        while (!extras.containsKey(extraOption)) {
            System.out.println("No such option, please try again");
            extraOption = scanner.nextInt();
        }
        return  extraOption;
    }
}
