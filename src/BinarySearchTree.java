/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/10
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    private BinaryNode<AnyType> root;

    public BinarySearchTree() {
        root = null;
    }

    //todo Internal method to find an item in a subtree.
    public boolean contains(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true;
    }

    //todo Internal method to find the smallest item in a subtree.
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        else
            return findMin(t.left);
    }

    //todo Internal method to find the largest item in a subtree.
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
        if (t == null)
            return null;

        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    //todo Internal method to insert into a subtree
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<AnyType>(x, null, null);//todo return the new root of the tree after insertion

        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ;//todo equal do nothing

        return t;
    }

    //todo Internal method to remove from a subtree.
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return null;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return t;

    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(AnyType x) {
        return contains(x, root);
    }

    public AnyType findMin() throws Exception {
        if (isEmpty())
            throw new Exception();
        return findMin(root).element;
    }

    public AnyType findMax() throws Exception {
        if (isEmpty())
            throw new Exception();
        return findMax(root).element;
    }

    public void insert(AnyType t) {
        root = insert(t, root);
    }

    public void remove(AnyType t) {
        root = remove(t, root);
    }

    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        public BinaryNode(T element) {
            this.element = element;
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }
}
