package ru.mail.polis.sort;


import java.lang.reflect.Array;


public class MergeSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  public void sort(T[] a) {
    int n = a.length;
    T[] t = (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);

    for (int len = 1; len < n; len *= 2) {
      for (int i = 0; i < n - len; i += len + len) {
        int mid = i + len - 1;
        int j = Math.min(i + len + len - 1, n - 1);
        merge(a, t, i, mid, j);
      }
    }
  }

  void merge(T[] a, T[] t, int left, int mid, int right) {
    for (int i = left; i <= right; i++) {
      t[i] = a[i];
    }
    int i = left, j = mid + 1;
    for (int k = left; k <= right; k++) {
      if (i > mid) {
        a[k] = t[j++];
      } else if (j > right) {
        a[k] = t[i++];
      } else if (lesser(t[j], t[i])) {
        a[k] = t[j++];
      } else {
        a[k] = t[i++];
      }
    }
  }
}
