package ru.otus.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.otus.DIYArrayList;
import ru.otus.DIYDoublingAppendArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHAppendArrayList {
    private static final int ARRAY_SIZE_MAX = 100_000;
    private List<Integer> diyArrayList;
    private List<Integer> arrayList;
    private List<Integer> diyDoublingAppendArrayList;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHAppendArrayList.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        arrayList = new ArrayList<>();
        diyArrayList = new DIYArrayList<>();
        diyDoublingAppendArrayList = new DIYDoublingAppendArrayList<>();
    }

    @Benchmark
    public void addArrayListTest() {
        for (int i = 1; i < ARRAY_SIZE_MAX; i++) {
            arrayList.add(i);
        }
    }

    @Benchmark
    public void addDIYArrayListTest() {
        for (int i = 1; i < ARRAY_SIZE_MAX; i++) {
            diyArrayList.add(i);
        }
    }

    @Benchmark
    public void addDIYDoublingAppendArrayListTest() {
        for (int i = 1; i < ARRAY_SIZE_MAX; i++) {
            diyDoublingAppendArrayList.add(i);
        }
    }
}
