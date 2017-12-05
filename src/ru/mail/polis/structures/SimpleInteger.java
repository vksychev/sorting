package ru.mail.polis.structures;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical, Comparable<SimpleInteger> {

  private static final int DIGIT_COUNT = 10;

  private final Integer data;
  private final int length;

  public SimpleInteger(Integer data) throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("Source must be not null");
    }
    this.data = data;
    this.length = String.valueOf(data).length();
  }

  @Override
  public int getDigit(int index) throws IndexOutOfBoundsException {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Negative index " + index);
    } else if (index >= getDigitCount()) {
      return 0;
    } else {
      return Integer.parseInt(String.valueOf(data.toString().charAt(index)));
    }
  }

  @Override
  public int getDigitMaxValue() {
    return DIGIT_COUNT;
  }

  @Override
  public int getDigitCount() {
    return length;
  }

  @Override
  public int compareTo(SimpleInteger anotherSimpleInteger) {
    return this.data.compareTo(anotherSimpleInteger.data);
  }

  @Override
  public String toString() {
    return data.toString();
  }
}
