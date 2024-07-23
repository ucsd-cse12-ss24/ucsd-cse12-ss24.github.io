package BSTMap;
// We are implementing an interface

class BSTMap<K extends Comparable<K>, V> implements DefaultMap<K, V> {
    Node<K, V> root;

    BSTMap() {
        this.root = null;
    }

    BSTMap(Node<K, V> root) {
        this.root = root;
    }

    V get(Node<K, V> node, K key) {
        if (node == null) {
            throw new IllegalArgumentException(ILLEGAL_ARG_NULL_KEY);
        }

        if (node.key.equals(key))
            return node.value;

        // Note we cannot use > here. What should we use?
        // if (node.key > key) {
        // return get(node.left, key);
        // } else {
        // return get(node.right, key);
        // }

        return null; // placeholder
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        return this.get(this.root, key);
    }

    // the implementation for `height` is the same as the one from lec. 7
    int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // right sub-tree is larger
        if (leftHeight < rightHeight) {
            return 1 + rightHeight;

            // left sub-tree is larger
        } else {
            return 1 + leftHeight;
        }
    }

    int height() {
        return this.height(this.root);
    }

    public void printAllElements() {
        printAllElements(this.root);
    }

    void printAllElements(Node<K, V> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.key);

        printAllElements(node.left);
        printAllElements(node.right);
    }

    Node<K, V> set(Node<K, V> node, K key, V value) {
        if (node == null) {
          return new Node<K, V>(key, value, null, null);
        }
        int comp = key.compareTo(node.key);

        if (comp < 0) { // key is smaller, should insert to the left sub tree
          node.right = this.set(node.left, key, value);
          return node;
        } else if (comp > 0) { // key is larger, should insert to the right sub tree
          node.left = this.set(node.left, key, value);
          return node;
        } else { // key is the same as the key of the current node
          node.value = value;
          return node;
        }
      }
      

    public static void main(String[] args) {
        Node<String, Integer> node = new Node<>("a", 10, null,
                new Node<>("c", 80,
                        new Node<>("b", 200, null, null),
                        new Node<>("g", 200, null, null)));

        BSTMap<String, Integer> bstm = new BSTMap<>(node);

        bstm.printAllElements();
    }
}