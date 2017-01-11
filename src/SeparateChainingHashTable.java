import java.util.LinkedList;
import java.util.List;

/**
 * Author: Spikerman < mail4spikerman@gmail.com >
 * Created Date: 17/1/11
 */
public class SeparateChainingHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<AnyType>[] theLists;
    private int currentSize;

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        theLists = new LinkedList[size];
        for (List l : theLists) {
            l = new LinkedList();
        }
    }

    public void makeEmpty() {
        for (List l : theLists) {
            l.clear();
        }
    }

    //todo Find an item in the hash table.
    public boolean contains(AnyType t) {
        List<AnyType> whichList = theLists[myHash(t)];
        return whichList.contains(t);
    }

    public void insert(AnyType t) {
        List<AnyType> whichList = theLists[myHash(t)];
        if (!whichList.contains(t)) {
            whichList.add(t);
            currentSize++;
        }
    }

    public void remove(AnyType t) {
        List<AnyType> whichList = theLists[myHash(t)];
        if (!whichList.contains(t)) {
            whichList.remove(t);
            currentSize--;
        }
    }

    private int myHash(AnyType t) {
        int hashVal = t.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }
}
