package ru.otus.executors;

import ru.otus.models.TestMetadata;
import ru.otus.parsers.ParserTestClass;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static ru.otus.util.ReflectionUtils.instantiate;

public class TestExecutor {
    private final PrintStream ps = System.out;
    private int completedTestsCount = 0;
    private int failedTestsCount = 0;

    public void executeAllTests(String className) throws Exception {
        TestMetadata testMetadata = ParserTestClass.parse(className);
        for (Method testMethod : testMetadata.getTestMethods()) {
            executeTestCycle(testMetadata, testMethod);
        }
        printConclusionInfo();
    }

    private void executeTestCycle(TestMetadata testMetadata, Method test) throws Exception {
        Class<?> clazz = testMetadata.getClazz();
        Object classTestInstance = instantiate(clazz);
        invoke(testMetadata.getBeforeMethod(), classTestInstance);
        invokeTest(test, classTestInstance);
        invoke(testMetadata.getAfterMethod(), classTestInstance);
    }

    private static void invoke(Method m, Object testInst) throws InvocationTargetException, IllegalAccessException {
        if (m != null) {
            m.invoke(testInst);
        }
    }

    private void invokeTest(Method t, Object testInst) {
        try {
            t.invoke(testInst);
            printPassedInfo(t);
            completedTestsCount++;
        } catch (Throwable e) {
            printFailedInfo(t, e.getCause());
            failedTestsCount++;
        }
    }

    private void printPassedInfo(Method t) {
        ps.println(t.getDeclaringClass().getSimpleName() + " > " + t.getName() + " PASSED");
    }

    private void printFailedInfo(Method t, Throwable ex) {
        ps.println("\n" + ex.getMessage());
        ex.printStackTrace(ps);
        ps.println(t.getDeclaringClass().getSimpleName() + " > " + t.getName() + " FAILED");
        ps.println("\t" + ex.getClass().getName() + " at "
                + ex.getStackTrace()[0].getFileName() + ":" + ex.getStackTrace()[0].getLineNumber());
    }

    private void printConclusionInfo() {
        ps.println(completedTestsCount + " tests completed, " + failedTestsCount + " failed");
    }
}
