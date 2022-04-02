package com.amihaescu.swissre;

import com.amihaescu.swissre.display.DisplayHelper;
import com.amihaescu.swissre.files.FileLoader;
import com.amihaescu.swissre.mappers.ExtrasMapper;
import com.amihaescu.swissre.mappers.ExtrasMapperImpl;
import com.amihaescu.swissre.mappers.ProductMapper;
import com.amihaescu.swissre.mappers.ProductMapperImpl;
import com.amihaescu.swissre.model.Extra;
import com.amihaescu.swissre.model.Order;
import com.amihaescu.swissre.model.Product;

import java.util.Map;
import java.util.Scanner;

public class Main {


    private static final ProductMapper productMapper = new ProductMapperImpl();
    private static final ExtrasMapper extrasMapper = new ExtrasMapperImpl();
    private static final FileLoader fileLoader = new FileLoader(extrasMapper, productMapper);
    private static final DisplayHelper displayHelper = new DisplayHelper();

    public static void main(String[] args) {
        final Map<Integer, Product> products = fileLoader.loadProducts();
        final Map<Integer, Extra> extras = fileLoader.loadExtras();
        Scanner scanner = new Scanner(System.in);
        int option;
        Order order = new Order();
        do {
            order.display(false);
            displayHelper.displayMenu(products, order);
            option = scanner.nextInt();
            if (order.containsItems() && option == 6) {
                order.display(true);
                order = new Order();
                continue;
            } else if (!products.containsKey(option)) {
                System.out.println("Invalid option");
                continue;
            }
            if (displayHelper.isEligibleForExtra(extras, option)) {
                System.out.println(products.get(option));
                System.out.print("Extras?[Y/N]");
                var extra = scanner.next().equals("Y");
                if (extra) {
                    displayHelper.displayExtras(extras);
                    var extraOption = displayHelper.processExtra(extras, scanner);
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
}
