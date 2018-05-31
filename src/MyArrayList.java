import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public int size() {
        return theSize;
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    private void ensureCapacity(int capacity) {
        if (capacity < theSize)
            return;
        T[] old = theItems;
        theItems = (T[]) new Object[capacity];
        for (int i = 0; i < theSize; i++) {
            theItems[i] = old[i];
        }
    }

    private void add(int idx, T newItem) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = theSize; i > idx; i--) {
            theItems[i] = theItems[i - 1];
        }
        theItems[idx] = newItem;
        theSize++;
    }

    public void add(T newItem) {
        add(size(), newItem);
    }

    public T remove(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        T old = theItems[idx];
        for (int i = idx; i < size() - 1; i++) {
            theItems[i] = theItems[i + 1];
        }
        theSize--;
        return old;
    }

    public T get(int idx) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        return theItems[idx];
    }

    public void set(int idx, T newItem) {
        if (idx < 0 || idx >= size())
            throw new ArrayIndexOutOfBoundsException();
        theItems[idx] = newItem;
    }


    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {
        int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}