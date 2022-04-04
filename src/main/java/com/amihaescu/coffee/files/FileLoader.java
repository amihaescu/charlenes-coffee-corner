package com.amihaescu.coffee.files;

import com.amihaescu.coffee.mappers.ExtrasMapper;
import com.amihaescu.coffee.mappers.ProductMapper;
import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public List<String> loadFile(String path) {
        try {
            InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(path);
            if (systemResourceAsStream == null) return Collections.emptyList();
            InputStreamReader streamReader = new InputStreamReader(systemResourceAsStream);
            BufferedReader reader = new BufferedReader(streamReader);
            var list = new ArrayList<String>();
            for (String line; (line = reader.readLine()) != null; ) {
                list.add(line);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Map<Integer, Extra> loadExtras() {
        return loadFile("extras.csv")
                .stream()
                .map(extrasMapper::toExtra)
                .collect(Collectors.toMap(Extra::getMenuItem, Function.identity()));
    }

    public Map<Integer, Product> loadProducts() {
        return loadFile("menu.csv")
                .stream()
                .map(productMapper::toProduct)
                .collect(Collectors.toMap(Product::getMenuItem, Function.identity()));
    }
}
