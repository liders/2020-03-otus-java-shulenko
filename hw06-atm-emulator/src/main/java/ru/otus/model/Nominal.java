package ru.otus.model;

import java.util.HashMap;
import java.util.Map;

public enum Nominal {
    N_10(10),
    N_20(20),
    N_50(50),
    N_100(100),
    N_500(500),
    N_1000(1000),
    N_5000(5000);

    private final int value;
    private static Map map = new HashMap<>();

    Nominal(int value) {
        this.value = value;
    }

    static {
        for (Nominal nominal: Nominal.values()) {
            map.put(nominal.value, nominal);
        }
    }

    public int getValue() {
        return value;
    }

    public static Nominal valueOf(int value) {
        return (Nominal) map.get(value);
    }
}
