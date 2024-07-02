package LinkedList;

class Node {
  String value;
  Node next;
  public Node(String value, Node next) {
    this.value = value;
    this.next = next;
  }
}

public class LinkedStringList implements StringList {

  Node front;
  int size;

  // How will we construct it?
  public LinkedStringList() {
    this.front = null;
    this.size = 0;
  }

  // How will we implement the methods?
  public void prepend(String s) {
    Node newFront = new Node(s, this.front);
    this.front = newFront;
    this.size += 1;
  }

  public String get(int index) {
    if (index >= this.size) return null;
    Node current = this.front;
    for(int i = 0; i < index; i += 1) {
      current = current.next;
    }
    return current.value;
  }

  public void add(String s) {

    Node current = this.front;

    if (current == null) {
      this.front = new Node(s, null);
      this.size += 1;
      return;
    }

    while(current.next != null) {
      current = current.next;
    }

    current.next = new Node(s, null);
    this.size += 1;
  }

  public void remove(int index) {
    Node current = this.front;
    for(int i = 0; i < index; i += 1) {
      current = current.next;
    }
    current.next = current.next.next;
	this.size -= 1;
  }

  public void insert(int index, String s) {
    Node current = this.front;
    for(int i = 0; i < index; i += 1) {
      current = current.next;
    }
    current.next = new Node(s, current.next);
	this.size += 1;
  }

  public int size() {
    return this.size;
  }

}

