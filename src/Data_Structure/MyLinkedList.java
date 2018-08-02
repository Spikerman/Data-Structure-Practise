package Data_Structure;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int theSize;
    private int modCnt;
    private Node<T> beginMarker;
    private Node<T> endMarker;

    MyLinkedList() {
        doClear();
    }

    // ==== public methods ====

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public void add(T newValue) {
        add(size(), newValue);
    }

    public void add(int idx, T newValue) {
        addBefore(getNode(idx, 0, size()), newValue); // 0 to size
    }

    public void remove(int idx) {
        remove(getNode(idx));
    }

    public T get(int idx) {
        return getNode(idx).data;
    }

    public void set(int idx, T newValue) {

    }

    // ==== private methods ====

    private void doClear() {
        modCnt = 0;
        theSize = 0;
        beginMarker = new Node(null, null, null);
        endMarker = new Node(null, beginMarker, null);
        beginMarker.next = endMarker;
    }

    private Node<T> getNode(int idx) {
        return getNode(idx, 0, size() - 1);
    }

    private Node<T> getNode(int idx, int lower, int upper) {
        if (idx < lower || idx > upper) throw new IndexOutOfBoundsException();
        Node<T> node;
        if (idx < size() / 2) {
            node = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
        } else {
            node = endMarker;
            for (int i = size(); i > idx; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private void addBefore(Node n, T newVal) {
        Node<T> newNode = new Node<T>(newVal, n.prev, n);
        newNode.prev.next = newNode;
        n.prev = newNode;
        theSize++;
        modCnt++;
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
        theSize--;
        modCnt++;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private static class Node<T> {
        T data;
        Node prev;
        Node next;

        Node(T newVal, Node prev, Node next) {
            this.data = newVal;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        boolean okToRemove = false;
        private Node<T> current = beginMarker.next;
        private int expectModCnt = modCnt;

        public boolean hasNext() {
            return current != endMarker;
        }

        public T next() {
            if (expectModCnt != modCnt) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (expectModCnt != modCnt) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
        }
    }
}


