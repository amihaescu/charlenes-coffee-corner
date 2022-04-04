package com.amihaescu.coffee.files;

import com.amihaescu.coffee.mappers.ExtrasMapper;
import com.amihaescu.coffee.mappers.ProductMapper;
import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Product;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileLoader {

    private final ExtrasMapper extrasMapper;
    private final ProductMapper productMapper;

    public FileLoader(ExtrasMapper extrasMapper, ProductMapper productMapper) {
        this.extrasMapper = extrasMapper;
        this.productMapper = productMapper;
    }

    public Stream<String> loadFile(String path) {
        try {
            URL systemResource = ClassLoader.getSystemResource(path);
            if (systemResource == null) return Stream.empty();
            return Files.lines(Paths.get(systemResource.toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    public Map<Integer, Extra> loadExtras() {
        return loadFile("extras.csv")
                .map(extrasMapper::toExtra)
                .collect(Collectors.toMap(Extra::getMenuItem, Function.identity()));
    }

    public Map<Integer, Product> loadProducts() {
        return loadFile("menu.csv")
                .map(productMapper::toProduct)
                .collect(Collectors.toMap(Product::getMenuItem, Function.identity()));
    }
}
