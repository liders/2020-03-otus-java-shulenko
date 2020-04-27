package ru.otus.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.otus.DIYArrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JMHArrayList {
    private static final int ARRAY_SIZE_MAX = 100_000;
    private List<Integer> diyArrayList;
    private List<Integer> arrayList;
    private List<Integer> copyDiyArrayList;
    private List<Integer> copyArrayList;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHArrayList.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() throws Exception {
        diyArrayList = fill(new DIYArrayList<>());
        arrayList = fill(new ArrayList<>());
        copyDiyArrayList = fill(new DIYArrayList<>());
        copyArrayList = fill(new ArrayList<>());
    }

    private List<Integer> fill(List<Integer> list) {
        for (int i = 0; i < ARRAY_SIZE_MAX; i++) {
            list.add(i);
        }
        return list;
    }

    @Benchmark
    public void getArrayListTest() {
        for (int i = 1; i < ARRAY_SIZE_MAX; i++) {
            arrayList.get(i);
        }
    }

    @Benchmark
    public void getDiyArrayListTest() {
        for (int i = 1; i < ARRAY_SIZE_MAX; i++) {
            diyArrayList.get(i);
        }
    }

    @Benchmark
    public void sortDiyArrayListTest() {
        diyArrayList.sort(Comparator.reverseOrder());
    }

    @Benchmark
    public void sortArrayListTest() {
        arrayList.sort(Comparator.reverseOrder());
    }

    @Benchmark
    public void copyDiyArrayListTest() {
        Collections.copy(copyDiyArrayList, diyArrayList);
    }

    @Benchmark
    public void copyArrayListTest() {
        Collections.copy(copyArrayList, arrayList);
    }
}
