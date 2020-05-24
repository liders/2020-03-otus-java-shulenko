Задание "Автоматическое логирование" 
---

Цель: Понять как реализуется AOP, какие для этого есть технические средства.
Разработайте такой функционал:
метод класса можно пометить самодельной аннотацией `@Log`, например, так:

```
class TestLogging {
    @Log
    public void calculation(int param) {
    };
}
```
При вызове этого метода "автомагически" в консоль должны логироваться значения параметров.
Например так.
````
class Demo {
    public void action() {
        new TestLogging().calculation(6);
    }
}
````
В консоле дожно быть:
executed method: calculation, param: 6

#### Сборка, запуск, вывод
```
$ ./gradlew build
$ java -jar ./hw05-automatic-logging/build/libs/autoLogging-0.0.1.jar 
executed method: calculationWithLog, param: [6]
```