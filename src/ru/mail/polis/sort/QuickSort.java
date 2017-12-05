package ru.mail.polis.sort;

import java.util.Comparator;
import java.util.Random;

public class QuickSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  private Random random = new Random();

  public QuickSort() {
    super();
  }

  public QuickSort(Comparator<? super T> comparator) {
    this.setComparator(comparator);
  }

  public void sort(T[] array) {
    sort(array, 0, array.length - 1);
  }

  public void sort(T[] array, int left, int right) {
    if (left >= right) {
      return;
    }

    if ((right - left) >= 10) {
      int idx = partition(array, left, right);

      sort(array, left, idx);
      sort(array, idx + 1, right);
    } else {
      insertSort(array, left, right);
    }
  }

  private int partition(T[] array, int left, int right) {
    T p = array[left + random.nextInt(right - left + 1)];
    int i = left, j = right;
    while (i <= j) {
      while (lesser(array[i], p)) {
        i++;
      }

      while (greater(array[j], p)) {
        j--;
      }

      if (i <= j) {
        swap(array, i++, j--);
      }

    }
    return j;
  }

  private void insertSort(T[] array, int left, int right) {
    for (int i = left + 1; i < right + 1; i++) {
      for (int j = i; j > left && lesser(array[j], array[j - 1]); j--) {
        swap(array, j, j - 1);
      }
    }
  }

}
