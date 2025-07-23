package deque;

import org.apache.bcel.generic.ANEWARRAY;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int capacity = 8;
    private static final int MIN_CAPACITY = 8;
    private int size;
    private T[] array;
    private int head;
    private int tail;
    private final int factor = 2;
    private final int fromFirst = 1;
    private final int fromLast = 2;
    public ArrayDeque61B() {
        size = 0;
        array = (T[]) new Object[capacity];
        tail = capacity / 2;
        head = capacity - tail;
    }


    @Override
    public void addFirst(T x) {
        if(size == capacity) {
            resize(capacity * factor);
        }
        head = Math.floorMod(head - 1, capacity);
        array[head] = x;
        size++;
    }

    void resize(int newCapacity) {
        T[]  newArray = (T[]) new Object[newCapacity];
        for(int i = 0; i < size; i++) {
            newArray[i] = array[Math.floorMod(head + i, capacity)];
        }
        array = newArray;
        capacity = newCapacity;
        head = 0;
        tail = size;
    }
    @Override
    public void addLast(T x) {
        if(size == capacity) {
            resize(capacity * factor);
        }
        array[tail] = x;
        tail = Math.floorMod(tail + 1, capacity);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for(int i = head; i != tail; i = Math.floorMod(i + 1, capacity)) {
            list.add(array[i]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T  x = array[head];
        head = Math.floorMod(head + 1, capacity);
        if(capacity > MIN_CAPACITY && size < capacity / 4) {
            resize(capacity / factor);
        }
        size--;
        return x;
    }

    @Override
    public T removeLast() {
        int temp = Math.floorMod(tail - 1, capacity);
        tail = Math.floorMod(tail - 1, capacity);
        T  x = array[temp];

        if(capacity > MIN_CAPACITY && size < capacity / 4) {
            resize(capacity / factor);
        }
        size--;
        return x;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0) {
            return null;
        }
        return array[Math.floorMod(head + index, capacity)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        private int index;
        @Override
        public boolean hasNext() {
            return index != size;
        }
        @Override
        public T next() {
            return get(index++);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque61B<?> other)) {
            return false;
        }
        if(this.size() != other.size()) {
            return false;
        }

        Iterator<T> thisIter = this.iterator();
        Iterator<?> otherIter = other.iterator();

        while (thisIter.hasNext() && otherIter.hasNext()) {
            T a = thisIter.next();
            Object b = otherIter.next();
            if (a == null) {
                if (b != null) return false;
            } else {
                if (!a.equals(b)) return false;
            }
        }

        return true;
    }
    @Override
    public String toString() {
        return toList().toString();
    }
}
