package ru.otus.parsers;

import ru.otus.models.TestMetadata;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParserTestClass {
    public static TestMetadata parse(String className) throws ClassNotFoundException {
        Objects.requireNonNull(className);

        TestMetadata testMetadata = new TestMetadata();
        List<Method> testMethods = new ArrayList<>();

        Class<?> clazz = Class.forName(className);
        for (Method m : clazz.getDeclaredMethods()) {
            m.setAccessible(true);
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            }
            if (m.isAnnotationPresent(Before.class)) {
                testMetadata.setBeforeMethod(m);
            }
            if (m.isAnnotationPresent(After.class)) {
                testMetadata.setAfterMethod(m);
            }
        }
        testMetadata.setTestMethods(testMethods);
        testMetadata.setClazz(clazz);

        return testMetadata;
    }
}
