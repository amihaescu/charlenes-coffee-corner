package com.amihaescu.coffee;

import com.amihaescu.coffee.display.DisplayHelper;
import com.amihaescu.coffee.files.FileLoader;
import com.amihaescu.coffee.mappers.ExtrasMapper;
import com.amihaescu.coffee.mappers.ExtrasMapperImpl;
import com.amihaescu.coffee.mappers.ProductMapper;
import com.amihaescu.coffee.mappers.ProductMapperImpl;
import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Order;
import com.amihaescu.coffee.model.Product;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final ProductMapper productMapper = new ProductMapperImpl();
    private static final ExtrasMapper extrasMapper = new ExtrasMapperImpl();
    private static final FileLoader fileLoader = new FileLoader(extrasMapper, productMapper);
    private static final DisplayHelper displayHelper = new DisplayHelper();
    private static final Map<Integer, Product> products = fileLoader.loadProducts();
    private static final Map<Integer, Extra> extras = fileLoader.loadExtras();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;
        Order order = new Order();
        do {
            System.out.println(order.toString(false));
            System.out.print(displayHelper.displayMenu(products, order));
            option = scanner.nextInt();
            if (option == -1) break;
            if (order.containsItems() && option == 0) {
                System.out.println(order.toString(true));
                order = new Order();
                continue;
            } else if (!products.containsKey(option)) {
                System.out.println("Invalid option");
                continue;
            }
            var extraOption = processExtraOption(scanner, option);
            if (extraOption != -1) {
                order.addProduct(extras.get(extraOption));
            }
            order.addProduct(products.get(option));
            if (order.isEligibleForFreeExtra()) {
                displayHelper.displayExtras(extras);
                var freeExtraOption = processFreeExtraOption(scanner);
                if (freeExtraOption != -1) {
                    order.addFreeExtra(extras.get(freeExtraOption));
                }
            }
        } while (true);
    }

    private static Integer processExtraOption(Scanner scanner, int option) {
        int extraOption = -1;
        if (displayHelper.isEligibleForExtra(Main.extras, option)) {
            System.out.println(Main.products.get(option));
            System.out.print("Extras?[Y/N]");
            var extra = "Y".equalsIgnoreCase(scanner.next());
            if (extra) {
                System.out.print(displayHelper.displayExtras(Main.extras));
                extraOption = scanner.nextInt();
                while (!Main.extras.containsKey(extraOption)) {
                    System.out.println("No such option, please try again");
                    extraOption = scanner.nextInt();
                }
            }
        }
        return extraOption;
    }

    private static Integer processFreeExtraOption(Scanner scanner) {
        System.out.println("Your order is eligible for a free extra");
        System.out.print(displayHelper.displayExtras(Main.extras));
        int extraOption = scanner.nextInt();
        while (!Main.extras.containsKey(extraOption)) {
            System.out.println("No such option, please try again");
            extraOption = scanner.nextInt();
        }
        return extraOption;
    }
}
