package ru.mail.polis.bench;


import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.IntKeyStringValueObject;


@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class CountingSortBenchmark {

  private final int N = 1000;
  private IntKeyStringValueObject[] a1, a3, a4, a5, a6;

  private CountingSort<IntKeyStringValueObject> sortInt = new CountingSort<IntKeyStringValueObject>();

  private IntKeyStringValueObject[] create(Integer[] a) {
    IntKeyStringValueObject[] res = new IntKeyStringValueObject[a.length];
    for (int i = 0; i < a.length; i++) {
      res[i] = new IntKeyStringValueObject(a[i], "Vadim");
    }
    return res;
  }

  @Setup(value = Level.Trial)
  public void setUpInvocation() {
    a1 = create(SortUtils.generateArrayIntegerNarrow(N));
    a3 = create(SortUtils.generateReverseHeap(N));
    a4 = create(SortUtils.generateArrayIntegerSorted(N));
    a5 = create(SortUtils.generateArrayIntegerUnsorted(N));
    a6 = create(SortUtils.generateArrayInteger(N));
  }

  @Benchmark
  public void measureNarrowRange(Blackhole bh) {
    sortInt.sort(a1);
    bh.consume(a1);
  }

  @Benchmark
  public void measureRevHeap(Blackhole bh) {
    sortInt.sort(a3);
    bh.consume(a3);
  }

  @Benchmark
  public void measureSorted(Blackhole bh) {
    sortInt.sort(a4);
    bh.consume(a4);
  }

  @Benchmark
  public void measureReverseSorted(Blackhole bh) {
    sortInt.sort(a5);
    bh.consume(a5);
  }

  @Benchmark
  public void measureRandInt(Blackhole bh) {
    sortInt.sort(a6);
    bh.consume(a6);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(CountingSortBenchmark.class.getSimpleName())
        .warmupIterations(5)
        .measurementIterations(5)
        .forks(1)
        .build();

    new Runner(opt).run();
  }
}