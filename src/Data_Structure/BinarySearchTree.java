package Data_Structure;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private Node<T> root;

    // ======== public method ========

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public boolean contains(T data) {
        return contains(data, root);
    }

    public void insert(T data) {
        insert(data, root);
    }

    public void remove(T data) {
        remove(data, root);
    }

    public T findMax() {
        if (isEmpty()) throw new IllegalStateException();
        return findMax(root);
    }

    public T findMin() {
        if (isEmpty()) throw new IllegalStateException();
        return findMin(root);
    }

    // ======== private method ========

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) return new Node<T>(data, null, null);
        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = insert(data, node.left);
        } else if (compareResult > 0) {
            node.right = insert(data, node.right);
        }
        // ignore duplicate
        return node;
    }

    private Node<T> remove(T data, Node<T> node) {
        if (node == null) return null;
        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            node.left = remove(data, node.left);
        } else if (compareResult > 0) {
            node.right = remove(data, node.right);
        } else if (node.left != null && node.right != null) {
            node.data = findMin(node.right);
            node.right = remove(node.data, node.right);
        } else {
            node = node.left != null ? node.left : node.right;
        }
        return node;
    }

    private boolean contains(T data, Node<T> node) {
        if (node == null) return false;
        int compareResult = data.compareTo(node.data);
        if (compareResult < 0) {
            return contains(data, node.left);
        } else if (compareResult > 0) {
            return contains(data, node.right);
        } else {
            return true;
        }
    }

    private T findMax(Node<T> node) {
        if (node == null) return null;
        if (node.right == null) return node.data;
        return findMax(node.right);
    }

    private T findMin(Node<T> node) {
        if (node == null) return null;
        if (node.left == null) return node.data;
        return findMin(node.left);
    }


    private static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node() {
            data = null;
            left = null;
            right = null;
        }
    }
}

// each public method has an corresponding private method (including node argument) returning node in order to perform recursion
// remove method has two situation, single child and two child
// before findMax and findMin, check if the tree is empty
// generic type T must extends from Comparable class so that it can invokes compareTo method