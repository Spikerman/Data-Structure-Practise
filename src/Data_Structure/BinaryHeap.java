package Data_Structure;

/**
 * Author: Spikerman
 * Created Date: 6/2/18
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] array;

    BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    BinaryHeap(int capacity) {
        theSize = 0;
        array = (T[]) new Comparable[capacity + 1];
    }

    //======== public method ========

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void clear() {
        theSize = 0;
    }

    public void insert(T newItem) {
        if (theSize == array.length - 1) {
            ensureCapacity(array.length * 2 + 1);
        }
        theSize++;
        int hole = theSize;
        while (hole > 1 && newItem.compareTo(array[hole / 2]) < 0) {
            array[hole] = array[hole / 2];
            hole /= 2;
        }
        array[hole] = newItem;
    }

    public T deleteMin() {
        if (isEmpty()) throw new IllegalStateException();
        T minItem = array[1];
        array[1] = array[theSize];
        theSize--;
        shiftDown(1);
        return minItem;
    }

    public T getMin() {
        if (isEmpty()) throw new IllegalStateException();
        return array[1];
    }

    //======== private method ========

    private void ensureCapacity(int capacity) {
        T[] oldArray = array;
        array = (T[]) new Comparable[capacity];
        for (int i = 0; i < oldArray.length; i++) {
            array[i] = oldArray[i];
        }
    }

    private void shiftDown(int hole) {
        T tmpItem = array[hole];
        while (hole * 2 <= theSize) {
            int child = hole * 2;
            if (child != theSize && array[hole + 1].compareTo(array[hole]) < 0) {
                child = child + 1;
            }
            if (tmpItem.compareTo(array[child]) > 0) {
                array[hole] = array[child];
            } else {
                break;
            }
            hole = child;
        }
        array[hole] = tmpItem;
    }


}
