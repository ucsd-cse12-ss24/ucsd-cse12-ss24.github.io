package BSTMap;

public class BT<K,V> {
    Node<K,V> root;
    BT() { this.root = null; }
    BT(Node<K,V> root) { this.root = root; }
  
    int countNodes(Node<K,V> node) {
        if (node == null) { return 0; }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
  
    int countNodes() {
        return this.countNodes(this.root);
    }
  
    V get(Node<K,V> node, K key) {
        if (node == null) { return null; }
        
        if (node.key.equals(key)) { return node.value; }
        
        V leftResult = get(node.left, key);
        V rightResult = get(node.right, key);
        
        if (leftResult != null) { return leftResult; }
        if (rightResult != null) { return rightResult; }
        
        return null;
    }
  
    V get(K key) {
        return this.get(this.root, key);
    }
  }