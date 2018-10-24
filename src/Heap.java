import java.util.ArrayList;
import java.util.Collections;

/**
 * Heap and min priority queue class, handles the main data structure used for this project
 *
 * @author Joseph Brooksbank
 */
public class Heap {
    /**
     * the main array which stores the heap
     */
    private ArrayList<Plane> planes;
    /**
     * the size of the heap inside the array, not always the same as the size of the array
     */
    int heapSize;

    /**
     * Constructor for building a heap out of a pre-existing array
     */
    Heap(ArrayList<Plane> planes) {
        this.planes = planes;
        heapSize = planes.size() - 1;
        buildMinHeap();
    }

    /**
     * Constructor for building a heap from nothing. I am using 1st indexed heaps, because it was easier to translate
     * from the textbook (and is more readable) rather than converting to 0 indexed heaps.
     */
    Heap() {
        this.planes = new ArrayList<>();
        planes.add(null);
        heapSize = planes.size() - 1;
    }

    /**
     * Method for getting index of the Parent node
     *
     * @param i Current index
     * @return The parent node's index
     */
    private int Parent(int i) {
        return i / 2;
    }

    /**
     * Method for getting the index of the Left child node
     *
     * @param i Current index
     * @return The left node's index
     */
    private int Left(int i) {
        return (i * 2);
    }

    /**
     * Method for gettting the index of the right child node
     *
     * @param i Current index
     * @return The right child's index
     */
    private int Right(int i) {
        return (i * 2) + 1;
    }

    /**
     * Method to "fix" a partially broken min heap, as seen in the textbook.
     *
     * @param i The index of the node to fix
     */
    private void minHeapifty(int i) {
        int smallest;
        int l = Left(i);
        int r = Right(i);
        if (l <= heapSize && planes.get(l).landingPriority < planes.get(i).landingPriority) {
            smallest = l;
        } else smallest = i;
        if (r <= heapSize && planes.get(r).landingPriority < planes.get(smallest).landingPriority) {
            smallest = r;
        }

        if (smallest != i) {
            Collections.swap(planes, i, smallest);
            minHeapifty(smallest);
        }
    }

    /**
     * A method to build a min heap from an unsorted array, as seen in the textbook
     */
    private void buildMinHeap() {
        for (int i = planes.size() / 2; i >= 1; i--) {
            minHeapifty(i);
        }
    }

    /**
     * Getter for minimum value of heap
     */
    Plane minimum() {
        return planes.get(1);
    }

    /**
     * Extractor for min value of heap as seen in textbook
     */
    Plane extractMin() throws HeapError {
        if (heapSize < 1) {
            throw new HeapError("heap size too small");
        }

        Plane min = planes.get(1);
        planes.set(1, planes.get(heapSize));
        heapSize--;
        minHeapifty(1);
        return min;
    }

    /**
     * Method to decrease the "key" value of a node, in this case the landing priority
     *
     * @param i   The index of the node to decrease
     * @param key The value which the key should be changed to
     * @throws HeapError If the previous key is already lower than the new value
     */
    private void decreaseKey(int i, double key) throws HeapError {
        if (key > planes.get(i).landingPriority)
            throw new HeapError("new key is larger than current key");

        planes.get(i).landingPriority = key;
        planes.get(i).baseLandingPriority = (int) key;

        while (i > 1 && planes.get(Parent(i)).landingPriority > planes.get(i).landingPriority) {
            Collections.swap(planes, i, Parent(i));
            i = Parent(i);
        }
    }

    /**
     * Method to insert new value into the heap
     *
     * @param key The new data to insert
     * @throws HeapError From Heap.decreaseKey
     */
    void insert(Plane key) throws HeapError {
        heapSize++;

        // Special case: the heap is empty
        if (planes.size() == heapSize) {
            planes.add(new Plane(key.aircraftID, key.arrivalTime, Integer.MAX_VALUE));
        } else planes.set(heapSize, new Plane(key.aircraftID, key.arrivalTime, Integer.MAX_VALUE));
        decreaseKey(heapSize, key.landingPriority);

    }

    public String toString() {
        return "Heap{" +
                "planes=" + planes +
                '}';
    }

    class HeapError extends Exception {
        HeapError(String msg) {
            super(msg);
        }
    }
}
