package ru.otus;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import com.google.common.math.Quantiles;

import java.util.Set;
import java.util.stream.IntStream;

public class HelloOtus {

    public static void main(String[] args) {
        Set<Integer> latencies = ContiguousSet.create(Range.closedOpen(0, 300), DiscreteDomain.integers());
        IntStream.range(0, 100)
                .forEach(i -> System.out.println("p" + i + " : " + Quantiles.percentiles().index(i).compute(latencies)));
    }

}
