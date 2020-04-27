package ru.otus;

import java.util.*;

public class DIYArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    Object[] elements = new Object[DEFAULT_CAPACITY];
    int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T t) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size + 1);
        }
        elements[size] = t;
        size = size + 1;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<? super T> c) {
        Arrays.sort((T[]) elements, 0, size, c);
    }

    @SuppressWarnings("unchecked")
    private T element(int index) {
        return (T) elements[index];
    }

    @Override
    public T get(int index) {
        return element(index);
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldElementValue = element(index);
        elements[index] = element;
        return oldElementValue;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public Iterator<T> iterator() {
        return new ListItr(0);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        for (int i = 0; i < elements.length; i++) {
            s.append(elements[i].toString());
            if (i < elements.length - 1) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }

    private class ListItr implements ListIterator<T> {
        int cursor;
        int lastRet = -1;

        public ListItr(int index) {
            cursor = index;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            int i = cursor;
            cursor = i + 1;
            return (T) elements[lastRet = i];
        }

        @Override
        public void set(T t) {
            DIYArrayList.this.set(lastRet, t);
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }


    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
