import java.util.*;

class Node<K, V> {
  K key;
  V value;
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
}

class Tree<K, V> {
  Node<K, V> root;

  Tree() {
    this.root = null;
  }

  Tree(Node<K, V> root) {
    this.root = root;
  }

  int countNodes(Node<K, V> node) {
    if (node == null) {
      return 0;
    }

    return 1 + countNodes(node.left) + countNodes(node.right);
  }

  int countNodes() {
    return this.countNodes(this.root);
  }

  V get(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    if (node.key.equals(key)) {
      return node.value;
    }

    // TASK
    V leftResult = get(node.left, key);
    V rightResult = get(node.right, key);

    if (leftResult != null) {
      return leftResult;
    }
    if (rightResult != null) {
      return rightResult;
    }
    // TASK END

    return null;
  }

  V get(K key) {
    return this.get(this.root, key);
  }
}

class BST<K extends Comparable<K>, V> {
  Node<K, V> root;

  BST() {
    this.root = null;
  }

  BST(Node<K, V> root) {
    this.root = root;
  }

  V get(K key) {
    // TASK
    return get(this.root, key);

  }
  
  // helper method
  V get(Node<K, V> node, K key) {
    if (node == null) {
      return null;
    }

    // search key less than node key -> go to left sub tree
    if (key.compareTo(node.key) < 0) {
      return get(node.left, key);

    } // key found
    else if (key.compareTo(node.key) == 0) {
      return node.value;
    }

    // search key greater than node key -> go to right sub tree
    else {
      return get(node.right, key);
    }


  }

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


}
