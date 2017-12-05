package ru.mail.polis.sort;


import java.util.Comparator;
import ru.mail.polis.collections.ArrayPriorityQueue;

public class HeapSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  private ArrayPriorityQueue<T> heap;

  public HeapSort() {
    heap = new ArrayPriorityQueue<>();
  }

  public HeapSort(Comparator<? super T> comparator) {
    this.comparator = comparator;
    heap = new ArrayPriorityQueue<>((Comparator<T>) comparator);
  }

  public void sort(T[] a) {
    for (int i = 0; i < a.length; i++) {
      heap.add(a[i]);
    }
    for (int i = 0; i < a.length; i++) {
      a[i] = heap.extractMin();
    }
  }
}
