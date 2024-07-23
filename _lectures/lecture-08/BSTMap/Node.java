package BSTMap;

public class Node<K, V> {
    public K key;
    public V value;
    Node<K, V> left;
    Node<K, V> right;

    public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public String toString() {
        String children = "";
        if (left != null || right != null) {
            children = ", " + left + ", " + right;
        }
        return "(" + key + ": " + value + children + ")";
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }
}