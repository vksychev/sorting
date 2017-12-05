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

import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.SortUtils;



@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class HeapSortBenchmark {

  private final int N = 1000;
  private Integer[] a1, a3, a4, a5, a6;
  private String[] a2, a7;

  private HeapSort<Integer> sortInteger = new HeapSort<>();
  private HeapSort<String> sortString = new HeapSort<>();

  @Setup(value = Level.Trial)
  public void setUpInvocation() {
    a1 = SortUtils.generateArrayIntegerNarrow(N);
    a2 = SortUtils.generateArrayStringLong(N);
    a3 = SortUtils.generateReverseHeap(N);
    a4 = SortUtils.generateArrayIntegerSorted(N);
    a5 = SortUtils.generateArrayIntegerUnsorted(N);
    a6 = SortUtils.generateArrayInteger(N);
    a7 = SortUtils.generateArrayString(N);
  }

  @Benchmark
  public void measureNarrowRange(Blackhole bh) {
    sortInteger.sort(a1);
    bh.consume(a1);
  }

  @Benchmark
  public void measureLongString(Blackhole bh) {
    sortString.sort(a2);
    bh.consume(a2);
  }

  @Benchmark
  public void measureRevHeap(Blackhole bh) {
    sortInteger.sort(a3);
    bh.consume(a3);
  }

  @Benchmark
  public void measureSorted(Blackhole bh) {
    sortInteger.sort(a4);
    bh.consume(a4);
  }

  @Benchmark
  public void measureReverseSorted(Blackhole bh) {
    sortInteger.sort(a5);
    bh.consume(a5);
  }

  @Benchmark
  public void measureRandInt(Blackhole bh) {
    sortInteger.sort(a6);
    bh.consume(a6);
  }

  @Benchmark
  public void measureRandString(Blackhole bh) {
    sortString.sort(a7);
    bh.consume(a7);
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(HeapSort.class.getSimpleName())
        .warmupIterations(5)
        .measurementIterations(5)
        .forks(1)
        .build();

    new Runner(opt).run();
  }
}