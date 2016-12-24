import java.util.Iterator;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 12/23/16
 */

public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public void clear() {
        doClear();
    }

    public void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize)
            return;
        T[] oldItems = theItems;
        theItems = (T[]) new Object[newCapacity];// Java 不存在对泛型数组的构建
        for (int i = 0; i < theSize; i++) {
            theItems[i] = oldItems[i];
        }
    }

    //get
    public T get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    //set
    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[idx];
        theItems[idx] = newVal;
        return old;
    }

    //add version 1
    public void add(int idx, T newVal) {
        if (theItems.length == size())
            ensureCapacity(2 * size() + 1);

        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newVal;
        theSize++;
    }

    //add version 2
    public void add(T newVal) {
        add(size(), newVal);
    }

    //remove
    public T remove(int idx) {
        T removedVal = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return removedVal;
    }

    public void trimToSize() {
        ensureCapacity(theSize);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
