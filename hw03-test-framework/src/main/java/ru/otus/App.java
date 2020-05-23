package ru.otus;

import ru.otus.executors.TestExecutor;

/**
 * Сборка и запуск:
 *     ./gradlew build
 *     java -jar -enableassertions ./hw03-test-framework/build/libs/TestExecutor-0.0.1.jar ru.otus.test.TestExample
 *
 * В {@code ru.otus.test.TestExample} используется assert.
 * Поэтому запускать {@code ru.otus.test.TestExample} необходимо с параметром -enableassertions.
 *
 * Если в тестах не используется assert, то параметр -enableassertions необязателен.
 */
public class App {
    public static void main(String[] args) throws Exception {
        new TestExecutor().executeAllTests(args[0]);
    }
}
