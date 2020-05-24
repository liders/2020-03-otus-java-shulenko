package ru.otus.autologging;

import ru.otus.autologging.annotation.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Ioc {
    private Ioc() {
    }

    static Service create() throws NoSuchMethodException {
        Class<?> interfaceClazz = Service.class;
        Service service = new TestLogging();
        List<Method> autoLoggingMethods = findAutoLoggingMethods(service.getClass(), interfaceClazz);
        InvocationHandler handler = new LoggerInvocationHandler(service, autoLoggingMethods);
        return (Service) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{interfaceClazz}, handler);
    }

    static class LoggerInvocationHandler implements InvocationHandler {
        private final Service service;
        private final List<Method> loggingMethods;

        LoggerInvocationHandler(Service service, List<Method> loggingMethods) {
            this.service = service;
            this.loggingMethods = loggingMethods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (loggingMethods.contains(method)) {
                System.out.println("executed method: " + method.getName() +
                        ", param: " + Arrays.toString(args));
            }
            return method.invoke(service, args);
        }
    }

    private static List<Method> findAutoLoggingMethods(Class<?> clazz, Class<?> interfaceClazz) throws NoSuchMethodException {
        List<Method> methods = new ArrayList<>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Log.class)) {
                methods.add(interfaceClazz.getDeclaredMethod(m.getName(), m.getParameterTypes()));
            }
        }
        return methods;
    }
}
