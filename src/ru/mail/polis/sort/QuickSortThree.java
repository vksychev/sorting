package ru.mail.polis.sort;

import java.util.Random;

public class QuickSortThree<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  private Random random = new Random();

  private class Result {
    public int first;
    public int second;

    Result(int f, int s) {
      this.first = f;
      this.second = s;
    }

  }

  public void sort(T[] array) {
    sort(array, 0, array.length - 1);
  }

  public void sort(T[] array, int left, int right) {
    if (left >= right) {
      return;
    }
    Result r = partition(array, left, right);
    sort(array, left, r.first - 1);

    sort(array, r.second +1, right);
  }

  private Result partition(T[] array, int left, int right) {
    swap(array, (left + random.nextInt(right - left + 1)), left);
    int i = left, lt = left, rt = right;
    if (greater(array[lt], array[rt])) {
      swap(array, lt, rt);
    }
    while (i <= rt) {
      if (lesser(array[i], array[lt])) {
        swap(array, i++, lt++);
      } else {
        if (greater(array[i], array[lt])) {
          swap(array, rt--, i);
        } else {
          i++;
        }
      }
    }
    Result r = new Result(lt, rt);

    return r;
  }

}
