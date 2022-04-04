package com.amihaescu.coffee.files;

import com.amihaescu.coffee.model.Extra;
import com.amihaescu.coffee.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileLoaderTest {

    private final FileLoader fileLoader = new FileLoader(input -> new Extra(), input -> new Product());

    @Test
    void returns_stream_of_existing_file() {
        var stringStream = fileLoader.loadFile("test-file.csv");
        assertEquals(stringStream.size(), 1);
    }

    @Test
    void returns_empty_strea_for_non_existing_file() {
        var stringStream = fileLoader.loadFile("inexisting-file.csv");
        assertEquals(stringStream.size(), 0);
    }
}