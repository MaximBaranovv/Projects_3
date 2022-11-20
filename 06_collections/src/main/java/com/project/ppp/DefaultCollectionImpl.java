package com.project.ppp;

import com.project.ppp.collections.DefaultCollection;
import com.project.ppp.collections.DefaultIterator;

import java.util.*;

public class DefaultCollectionImpl<E> implements DefaultCollection<E> {
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private final static int DEFAULT_SIZE = 10;
    private final int arraySize = DEFAULT_SIZE;
    int lastRet = -1;
    int modCount = 0;
    int expectedModCount = modCount;
    private Object[] values;
    private int size;

    public DefaultCollectionImpl() {
        values = new Object[DEFAULT_SIZE];
    }

    public DefaultCollectionImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.values = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.values = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    private void resize() {
        if (size >= arraySize) {
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
        if (size >= DEFAULT_SIZE && size < arraySize / 4) {
            Object[] newValues = new Object[size * 3 / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
    }

    int indexOfRange(Object o, int start, int end) {
        Object[] es = values;
        if (o == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public DefaultIteratorImpl<E> iterator() {
        return new DefaultIteratorImpl<>();
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(values, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            return (T[]) Arrays.copyOf(values, size, a.getClass());
        }
        System.arraycopy(values, 0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E object) {
        resize();
        values[size] = object;
        size++;
        return true;
    }

    //VOPROS
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            size--;
            values[size] = null;
            return true;
        }
        return false;
    }

    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = values;

        E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    private void fastRemove(Object[] es, int i) {
        modCount++;
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    public void set(E e) {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            DefaultCollectionImpl.this.set(lastRet, e);
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }

    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        E oldValue = values(index);
        values[index] = element;
        return oldValue;
    }

    E values(int index) {
        return (E) values[index];
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }

    public boolean add(int index, E e) {
        resize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = e;
        ++size;
        return true;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> e = c.iterator();
        while (e.hasNext())
            if (!contains(e.next()))
                return false;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        if (c.isEmpty()) {
            return false;
        }
        for (E item : c) {
            add(item);
        }
        return true;
    }




    @Override
    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false, 0, size);
    }

    boolean batchRemove(Collection<?> c, boolean complement,
                        final int from, final int end) {
        Objects.requireNonNull(c);
        final Object[] es = values;
        int r;
        for (r = from; ; r++) {
            if (r == end)
                return false;
            if (c.contains(es[r]) != complement)
                break;
        }
        int w = r++;
        try {
            for (Object e; r < end; r++)
                if (c.contains(e = es[r]) == complement)
                    es[w++] = e;
        } catch (Throwable ex) {

            System.arraycopy(es, r, es, w, end - r);
            w += end - r;
            throw ex;
        } finally {
            modCount += end - w;
            shiftTailOverGap(es, w, end);
        }
        return true;
    }

    private void shiftTailOverGap(Object[] es, int lo, int hi) {
        System.arraycopy(es, hi, es, lo, size - hi);
        for (int to = size, i = (size -= hi - lo); i < to; i++)
            es[i] = null;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException();
        }
        boolean changed = false;
        for (int i = size() - 1; i >= 0; i--) {
            Object obj = get(i);
            if (!c.contains(obj)) {
                remove(i);
                changed = true;
            }
        }
        return changed;
    }

    public Object get(int index) {
        if (index < size && index >= 0) {
            return values[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            values[i] = null;
        }
        size = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                stringBuilder.append(values[i]);
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(values[i]);
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    class DefaultIteratorImpl<E> implements DefaultIterator<E> {

        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public boolean hasPrevious() {
            return index > 0;
        }


        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (E) values[index++];
        }


        public Object previous() {
            if (!hasPrevious()) {
                throw new IndexOutOfBoundsException();
            }
            return values[--index];
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }
    }
}
