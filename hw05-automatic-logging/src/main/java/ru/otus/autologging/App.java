package ru.otus.autologging;

public class App {
    public static void main(String[] args) throws Exception {
        Service service = Ioc.create();
        service.calculationWithLog(6);
        service.calculationWithoutLog(6);
    }
}
