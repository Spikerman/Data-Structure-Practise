import java.util.Iterator;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 12/24/16
 */
public class MyLinkedList<T> implements Iterable<T> {
    private int theSize;
    private int modCount = 0;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    public MyLinkedList() {
        doClear();
    }

    public void doClear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    //add 1
    public void add(int idx, T x) {
        addBefore(getNode(idx), x);
    }

    //add 2
    public void add(T x) {
        addBefore(getNode(size()), x);
    }

    //get
    public T get(int idx) {
        return getNode(idx).data;
    }

    public T set(int idx, T newVal) {
        Node<T> p = getNode(idx);
        T oldValue = p.data;
        p.data = newVal;
        return oldValue;
    }

    private void addBefore(Node<T> p, T x) {
        p.prev.next = p.prev = new Node<T>(x, p.prev, p);// brief case
        theSize++;
        modCount++;
    }

    //remove
    public T remove(int idx) {
        return remove(getNode(idx));
    }

    private T remove(Node<T> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        modCount++;
        theSize--;
        return p.data;
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        Node<T> p;
        if (idx < lower || idx > upper)
            throw new IndexOutOfBoundsException();
        if (idx < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarker.prev;
            for (int i = size(); i > idx; i++) {
                p = p.prev;
            }
        }
        return p;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
