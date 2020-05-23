package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestExample {
    private int n;

    @Before
    void setup() {
        n = 1;
    }

    @Test
    void shouldAssertError() {
        assert n == 2 : "Error";
    }

    @Test
    void shouldThrowRuntimeException() {
        throw new RuntimeException("FAILED");
    }

    @Test
    void shouldDiffInitValue() {
        n = n + 10;
        assert n == 11 : "Error";
    }

    @Test
    void shouldEqualOne() throws Exception {
        assert n == 1 : "Error";
    }

    @After
    void tearDown() {
        n = 0;
    }
}
