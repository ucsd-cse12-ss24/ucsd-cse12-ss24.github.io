package ArrayList;
  
public class ArrayStringList implements StringList {

  String[] elements;
  int size;				// How many elements are in the list?

  // How will we construct it?
  public ArrayStringList() {
	  this.elements = new String[2];	// Capacity of 2
	  this.size = 0;					// Number of elements added
  }

  public ArrayStringList(int capacity) {
	  this.elements = new String[capacity];
	  this.size = 0;
  }

  // How will it implement the methods?
  public void add(String s) {
	  expandCapacity();
	  this.elements[this.size] = s;
	  this.size += 1;
  }

  public void remove(int index) {
    // TODO: assumes index in bound; 
    // should throw an exception when removing from an empty list
   
    // shifts elements forward
    for(int i = index; i < this.size - 1; i++){
      this.elements[i] = this.elements[i + 1];
    }
	  
    // if removing from a full list,
    // ensure the last element is null
    this.elements[this.size - 1] = null;
    this.size -= 1;
  }

  
  // Assumes a valid index is given
  public void insert(int index, String s){
    expandCapacity();
	  
    // shifts elements backward
    for(int i = this.size; i > index; i--){
      this.elements[i] = this.elements[i - 1];
    }
    
    this.elements[index] = s;
    this.size += 1;

  }
  
  //Just call insert at index 0!
  public void prepend(String s){
	  this.insert(0, s);
	  return;
  }
  
  public String get(int index) {
	  return this.elements[index];
  }
  
  public int size() {
	  return this.size;
  }
  
  private void expandCapacity() {

	  int currentCapacity = this.elements.length;
	  if(this.size < currentCapacity) { return; }

	  String[] expanded = new String[currentCapacity * 2];

	  for(int i = 0; i < this.size; i += 1) {
		  expanded[i] = this.elements[i];
	  }

	  this.elements = expanded;
  }
}
