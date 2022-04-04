package com.amihaescu.coffee.files;

import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Product;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderTest {

    private final FileLoader fileLoader = new FileLoader(input -> new Extra(), input -> new Product());

    @Test
    void returns_stream_of_existing_file() {
        Stream<String> stringStream = fileLoader.loadFile("test-file.csv");
        assertEquals(stringStream.collect(Collectors.toSet()).size(), 1);
    }

    @Test
    void returns_empty_strea_for_non_existing_file() {
        Stream<String> stringStream = fileLoader.loadFile("inexisting-file.csv");
        assertEquals(stringStream.collect(Collectors.toSet()).size(), 0);
    }
}