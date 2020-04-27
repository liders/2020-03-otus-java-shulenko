package ru.otus;

import java.util.Arrays;

public class DIYDoublingAppendArrayList<T> extends DIYArrayList<T>{

    public DIYDoublingAppendArrayList() {
        super();
    }

    @Override
    public boolean add(T t) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size << 1);
        }
        elements[size] = t;
        size = size + 1;
        return true;
    }

}
