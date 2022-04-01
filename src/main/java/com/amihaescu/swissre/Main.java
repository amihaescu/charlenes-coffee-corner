package com.amihaescu.swissre;

import com.amihaescu.swissre.mappers.ExtrasMapper;
import com.amihaescu.swissre.mappers.ProductMapper;
import com.amihaescu.swissre.model.Extra;
import com.amihaescu.swissre.model.Order;
import com.amihaescu.swissre.model.Product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final ProductMapper productMapper = new ProductMapper();
    private static final ExtrasMapper extrasMapper = new ExtrasMapper();
    private static final Map<Integer, Product> products = loadProducts();
    private static final Map<Integer, Extra> extras = loadExtras();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        Order order = new Order();
        do {
            order.display();
            displayMenu();
            option = scanner.nextInt();
            if (!products.containsKey(option)) {
                System.out.println("Invalid option");
                continue;
            }
            order.addProduct(products.get(option));
        } while (option != -1);
    }

    private static void displayMenu() {
        System.out.println("Welcome to Charlene's Coffee Corner\n");
        products.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue).forEach(System.out::println);
        System.out.println("\nPlease choose item: ");
    }

    private static Map<Integer, Extra> loadExtras() {
        return loadFile("extras.csv")
                .map(extrasMapper::toExtra)
                .collect(Collectors.toMap(Extra::getMenuItem, Function.identity()));
    }

    private static Map<Integer, Product> loadProducts() {
        return loadFile("menu.csv")
                .map(productMapper::toProduct)
                .collect(Collectors.toMap(Product::getMenuItem, Function.identity()));
    }

    private static Stream<String> loadFile(String path) {
        try {
            return Files.lines(Paths.get(ClassLoader.getSystemResource(path)
                    .toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }


}
