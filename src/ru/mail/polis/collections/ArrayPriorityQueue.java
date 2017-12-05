package ru.mail.polis.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ru.mail.polis.sort.SortUtils;


public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

  private int DEFAULT_CAPACITY = 10;
  private Key[] elementData;
  private Comparator<Key> comparator;
  private int size;
  private int tail = -1;

  public ArrayPriorityQueue() {
    this.elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    size = 0;
  }

  public ArrayPriorityQueue(Comparator<Key> comparator) {
    this.elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    size = 0;
    this.comparator = comparator;
  }

  @Override
  public void add(Key key) {
    elementData[size] = key;
    siftUp();
    size++;
    if (size == elementData.length) grow();
  }

  @Override
  public Key peek() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return elementData[0];
  }

  void swap(int i, int j) {
    Key t = elementData[i];
    elementData[i] = elementData[j];
    elementData[j] = t;
  }

  @Override
  public Key extractMin() {
    if (isEmpty()) {
      throw new ArrayIndexOutOfBoundsException("extract min from empty queue");
    }
    Key removed = elementData[0];
    elementData[0] = elementData[--size];
    siftDown();
    return removed;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public int size() {
    return size;
  }

  private void siftUp() {
    int pos = size;
    while (pos > 0) {
      int parent = (pos - 1) / 2;
      if (elementData[pos].compareTo(elementData[parent]) >= 0)
        break;
      swap(pos, parent);
      pos = parent;
    }
  }

  private void siftDown() {
    int pos = 0;
    while (true) {
      int child = 2 * pos + 1;
      if (child >= size)
        break;
      if (child + 1 < size && elementData[child + 1].compareTo(elementData[child]) < 0)
        ++child;
      if (elementData[pos].compareTo(elementData[child]) <=0)
        break;
      swap(pos, child);
      pos = child;
    }
  }

  private void grow() {
    this.changeCapacity((int) Math.abs(size * 1.5));
  }

  private void shrink() {
    int n = Math.abs(size / 2);
    if (n < DEFAULT_CAPACITY) {
      n = DEFAULT_CAPACITY;
    }
    this.changeCapacity(n);
  }

  private void changeCapacity(int newCapacity) {
    elementData = Arrays.copyOf(elementData, newCapacity);
  }


  @Override
  public Iterator<Key> iterator() {
    return new ArrayPriorityQueueIterator();
  }


  private boolean greater(int i, int j) {
    return comparator == null
        ? elementData[i].compareTo(elementData[j]) > 0
        : comparator.compare(elementData[i], elementData[j]) > 0
        ;
  }

  public Key[] getElementData() {
    return elementData;
  }

  private class ArrayPriorityQueueIterator implements Iterator<Key> {

    int index = 0;

    @Override
    public boolean hasNext() {
      return (index < tail);
    }

    @Override
    public Key next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return elementData[index++];
    }
  }

}