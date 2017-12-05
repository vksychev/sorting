package ru.mail.polis.sort;


import java.lang.reflect.Array;

import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;


public class LSDSort<T extends Numerical> extends AbstractSortOnComparisons<T> {


  public void sort(T[] array) {
    final int r = array[0].getDigitMaxValue() + 1;
    int d = findMax(array);
    for (int k = 0; k < d; k++) {
      int[] count = new int[r];
      for (T x : array) {
        count[x.getDigit(k)]++;
      }
      for (int i = 1; i < r; i++) {
        count[i] += count[i - 1];
      }
      T[] res = (T[]) new Numerical[array.length];
      for (int i = array.length - 1; i >= 0; i--) {
        res[--count[array[i].getDigit(k)]] = array[i];
      }
      System.arraycopy(res, 0, array, 0, array.length);
    }
  }

  private int findMax(T[] a) {
    int max = 0;
    for (T x : a) {
      max = Math.max(max, x.getDigitCount());
    }
    return max;
  }


}
