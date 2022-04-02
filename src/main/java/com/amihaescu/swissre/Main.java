package com.amihaescu.swissre;

import com.amihaescu.swissre.files.FileLoader;
import com.amihaescu.swissre.mappers.ExtrasMapper;
import com.amihaescu.swissre.mappers.ExtrasMapperImpl;
import com.amihaescu.swissre.mappers.ProductMapper;
import com.amihaescu.swissre.mappers.ProductMapperImpl;
import com.amihaescu.swissre.model.Extra;
import com.amihaescu.swissre.model.Order;
import com.amihaescu.swissre.model.Product;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {


    private static final ProductMapper productMapper = new ProductMapperImpl();
    private static final ExtrasMapper extrasMapper = new ExtrasMapperImpl();
    private static final FileLoader fileLoader = new FileLoader(extrasMapper, productMapper);

    public static void main(String[] args) {
        final Map<Integer, Product> products = fileLoader.loadProducts();
        final Map<Integer, Extra> extras = fileLoader.loadExtras();
        Scanner scanner = new Scanner(System.in);
        int option;
        Order order = new Order();
        do {
            order.display(false);
            displayMenu(products, order);
            option = scanner.nextInt();
            if (order.containsItems() && option == 6) {
                order.display(true);
                order = new Order();
                continue;
            } else if (!products.containsKey(option)) {
                System.out.println("Invalid option");
                continue;
            }
            if (isEligibleForExtra(extras, option)) {
                System.out.println(products.get(option));
                System.out.println("Extras?[Y/N]");
                var extra = scanner.next().equals("Y");
                if (extra) {
                    displayExtras(extras);
                    var extraOption = scanner.nextInt();
                    order.addProduct(products.get(option));
                    order.addProduct(extras.get(extraOption));
                    continue;
                }
                order.addProduct(products.get(option));
                continue;
            }
            order.addProduct(products.get(option));
        } while (option != -1);
    }

    private static void displayExtras(Map<Integer, Extra> extras) {
        extras.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).forEach(System.out::println);
        System.out.println("\nPlease choose item: ");
    }

    private static boolean isEligibleForExtra(Map<Integer, Extra> extras, int option) {
        return extras.values().stream().flatMap(extra -> extra.getForProduct().stream()).collect(Collectors.toSet()).contains(option);
    }

    private static void displayMenu(Map<Integer, Product> products, Order order) {
        System.out.println("Welcome to Charlene's Coffee Corner\n");
        products.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).forEach(System.out::println);
        if (order.containsItems()) {
            System.out.println("6 - Pay order");
        }

        System.out.println("\nPlease choose item: ");
    }


}
