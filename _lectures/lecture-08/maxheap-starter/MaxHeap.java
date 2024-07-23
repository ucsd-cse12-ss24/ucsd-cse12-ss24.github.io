import java.util.ArrayList;
import java.util.List;

class Node<K, V> {
    public K key;
    public V value;


    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "(" + key + ": " + value + ")";
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}

public class MaxHeap<K extends Comparable<K>, V> {

    List<Node<K, V>> entries;

    public MaxHeap()  {
        this.entries = new ArrayList<>();
    }

    int left(int index) {
        // TASK: return the index of the left child
        return 0; 
    }

    int right(int index) {
        // TASK: return the index of the right child
        return 0;
    }

    int parent(int index) {
        // TASK: return the index of the parent node
        return 0;
    }

    private boolean existsAndGreater(int childIndex, int parentIndex) {
        // Check if the child index is within the bounds of the heap
        if (childIndex >= this.entries.size()) {
            return false;
        }

        // Check if the key at childIndex is greater than the value at parentIndex
        K childKey = this.entries.get(childIndex).getKey();
        K parentKey = this.entries.get(parentIndex).getKey();

        return childKey.compareTo(parentKey) > 0;
    }

    private void swap(int index1, int index2) {
        Node<K, V> node1 = entries.get(index1);
        Node<K, V> node2 = entries.get(index2);
        entries.set(index1, node2);
        entries.set(index2, node1);
    }

    V get(int index, K key) {
        if (index >= this.entries.size()) {
            return null;
        }
        Node<K, V> entry = null; // TASK: ______

        if (entry.getKey().equals(key)) {
            return entry.getValue();
        }

        V leftResult = null; // TASK: ______
        V rightResult = null; // TASK: ______
        if (leftResult != null) {
            return leftResult;
        }
        if (rightResult != null) {
            return rightResult;
        }
        return null;
    }

    void add(K k, V v) {
        // TASK
    }

    K poll() {
        // heap is empty
        if (this.entries.size() == 0) {
            return null;
        }

        // TASK case 1: heap has only one root node

        // TASK case 2: heap has more than one node

        return null; // placeholder
    }

    void bubbleDown(int index) {
        // return if reaching the end of the heap
        if (index >= this.entries.size()) {
            return;
        }

        // return if no left child node
        int leftIndex = left(index);
        if (leftIndex >= this.entries.size()) {
            return;
        }

        // TASK
    }

    void bubbleUp(int index) {
        // return if reaching the root node
        if (index <= 0) {
            return;
        }
        
        // TASK
    }

    public static void main(String[] args) {
        MaxHeap<String, Integer> heap = new MaxHeap<>();

        // Test case 1: Insert elements
        heap.add("apple", 10);
        heap.add("banana", 20);
        heap.add("cherry", 15);

        System.out.println("Max key: " + heap.poll()); // Output: cherry

        // Test case 2: Insert more elements
        heap.add("date", 30);
        heap.add("elderberry", 25);

        System.out.println("Max key: " + heap.poll()); // Output: elderberry
        System.out.println("Max key: " + heap.poll()); // Output: date
        System.out.println("Max key: " + heap.poll()); // Output: banana

        // Test case 3: Extract remaining elements
        System.out.println("Max key: " + heap.poll()); // Output: apple
        System.out.println("Max key: " + heap.poll()); // Output: null (heap is empty)
    }
}