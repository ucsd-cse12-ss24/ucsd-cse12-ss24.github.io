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
        return index * 2 + 1;
    }

    int right(int index) {
        return index * 2 + 2;
    }

    int parent(int index) {
        if (index % 2 == 0) {
            return (index - 2) / 2;
        } else {
            return (index - 1) / 2;
        }
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
        Node<K, V> entry = entries.get(index);

        if (entry.getKey().equals(key)) {
            return entry.getValue();
        }

        V leftResult = get(left(index), key);
        V rightResult = get(right(index), key);
        if (leftResult != null) {
            return leftResult;
        }
        if (rightResult != null) {
            return rightResult;
        }
        return null;
    }

    void add(K k, V v) {
        Node<K, V> newNode = new Node<>(k, v);
        entries.add(newNode);
        bubbleUp(entries.size() - 1);
    }

    K poll() {
        if (this.entries.size() == 0) {
            return null;
        }

        Node<K, V> origRoot = this.entries.get(0);
        if (this.entries.size() > 1) {
            // root has children

            Node<K, V> last = this.entries.remove(this.entries.size() - 1);
            this.entries.set(0, last);
            bubbleDown(0);
        } else {
            // root is the only node
            this.entries.remove(0);
        }

        return origRoot.getKey();
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

        int largerChildIndex = leftIndex;
        int rightIndex = right(index);

        // if right child is larger than left child, then it is more suitable for the
        // swap
        if (existsAndGreater(rightIndex, leftIndex)) {
            largerChildIndex = rightIndex;
        }
        // if the larger child is larger than the current node, then swap them
        if (existsAndGreater(largerChildIndex, index)) {
            swap(index, largerChildIndex);
            bubbleDown(largerChildIndex);
        }
    }

    void bubbleUp(int index) {
        // return if reaching the root node
        if (index <= 0) {
            return;
        }
        Node<K, V> e = this.entries.get(index);
        Node<K, V> parent = this.entries.get(parent(index));

        if (e.key.compareTo(parent.key) > 0) {
            swap(index, parent(index));
            bubbleUp(parent(index));
        } else {
            return;
        }
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