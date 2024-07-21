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

    // TASK: understand what the line below does
    return 1 + countNodes(node.left) + countNodes(node.right);
  }

  int countNodes() {
    return this.countNodes(this.root);
  }

  V get(Node<K, V> node, K key) {

    // empty node
    if (node == null) {
      return null;
    }


    // node has the key
    if (node.key.equals(key)) {
      return node.value;
    }


    // TASK: implement `get` for the case 
    // where node is not null and doesn't have the key


    // key not found in tree
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
    return null; // TASK: REPLACE ME

    // hint: to compare key1 to key2,
    // we can use key1.compareTo(key2) because K is a comparable type
    // check the documentation for compareTo for usage

  }


  int height() {
    return 0; // TASK: REPLACE ME
  }

}
