package ru.otus.models;

import java.lang.reflect.Method;
import java.util.List;

public class TestMetadata {
    private Class<?> clazz;
    private Method beforeMethod;
    private Method afterMethod;
    private List<Method> testMethods;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getBeforeMethod() {
        return beforeMethod;
    }

    public void setBeforeMethod(Method beforeMethod) {
        this.beforeMethod = beforeMethod;
    }

    public Method getAfterMethod() {
        return afterMethod;
    }

    public void setAfterMethod(Method afterMethod) {
        this.afterMethod = afterMethod;
    }

    public List<Method> getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(List<Method> testMethods) {
        this.testMethods = testMethods;
    }
}
