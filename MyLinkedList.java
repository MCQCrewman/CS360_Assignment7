//Evan McQueary
//CS360 Assignment 7
/* MyLinkedList is an implementation of the linked list which supports and provides
basic list operations. The class extends MyAbstractList. The class includes methods
for list operations such as getting, setting, and removing elements
at specific indexes, as well as finding the index of a particular element.
The class also supports adding elements to the beginning or end of the list,
removing elements from the beginning or end, and reversing the order of elements.*/

import java.util.NoSuchElementException;

public class MyLinkedList<E> extends MyAbstractList<E> {
  private Node<E> head, tail;

  /** Create a default list */
  public MyLinkedList() {
  }

  /** Create a list from an array of objects */
  public MyLinkedList(E[] objects) {
        super(objects);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
    	throw new NoSuchElementException();
    }
    else {
      return tail.element;
    }
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new node
    newNode.next = head; // Link the new node with the head
    head = newNode; // head points to the new node
    size++; // Increase list size

    if (tail == null) // If the new node is the only node in list
      tail = head;
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new for element e

    if (tail == null) {
      head = tail = newNode; // The new node is the only node in list
    }
    else {
      tail.next = newNode; // Link the new with the last node
      tail = tail.next; // tail now points to the last node
    }

    size++; // Increase size
  }

  @Override /** Add a new element at the specified index
   * in this list. The index of the head element is 0 */
  public void add(int index, E e) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    else if (index == 0) {
      addFirst(e);
    }
    else if (index == size) {
      addLast(e);
    }
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) { // Traverse list to position before insertion point
        current = current.next;
      }
      Node<E> temp = current.next; // Insert new node between current and current.next
      current.next = new Node<E>(e);
      (current.next).next = temp;
      size++;
    }
  }

  /** Remove the head node and
   *  return the object that is contained in the removed node. */
  public E removeFirst() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    else {
      Node<E> temp = head;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      return temp.element;
    }
  }

  /** Remove the last node and
   * return the object that is contained in the removed node. */
  public E removeLast() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    else if (size == 1) {
      Node<E> temp = head;
      head = tail = null;
      size = 0;
      return temp.element;
    }
    else {
      Node<E> current = head;

      for (int i = 0; i < size - 2; i++) { // Traverse list elements until current is second to last node in list
        current = current.next;
      }

      Node<E> temp = tail;
      tail = current;
      tail.next = null;
      size--;
      return temp.element;
    }
  }

  @Override /** Remove the element at the specified position in this 
   *  list. Return the element that was removed from the list. */
  public E remove(int index) {
    checkIndex(index);
    
    if (index == 0) {
      return removeFirst();
    }
    else if (index == size - 1) {
      return removeLast();
    }
    else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      size--;
      return current.element;
    }
  }

  @Override /** Override toString() to return elements in the list */
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      }
    }
    result.append("]"); // Insert the closing ] in the string
    return result.toString();
  }

  @Override /** Clear the list */
  public void clear() {
    size = 0;
    head = tail = null;
  }

  @Override /** Return true if this list contains the element e */
  public boolean contains(E e) {
    //This statement is no longer needed: System.out.println("Implementation for this assignment, part 1");

    // Use the iterator to traverse the linkedlist.
    LinkedListIterator iterator = new LinkedListIterator();

    // Check if the list contains the specified element (using iterator).
    while (iterator.hasNext()) {
      if (iterator.next().equals(e)) {
        return true;
      }
    }

    // If it does not have the element, return false.
    return false;
  }

  @Override /** Return the element at the specified index */
  public E get(int index) {
    //This statement is no longer needed: System.out.println("Implementation for assignment, part 2");

    // Check if the index is within bounds.
    checkIndex(index);

    // Use iterator to traverse the linkedlist to the specified index.
    //Invoke iterator.
    LinkedListIterator iterator = new LinkedListIterator();
    Node<E> current = iterator.current;

    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    // Return the element at the specified index
    return current.element;
  }

  @Override /** Return the index of the first matching element in
   *  this list. Return -1 if no match. */
  public int indexOf(E e) {
    //This statement is no longer needed: System.out.println("Implementation for assignment, part 3");

    // Use iterator to traverse the linkedlist.
    LinkedListIterator iterator = new LinkedListIterator();
    int index = 0;

    // Check if the list contains the specified element.
    while (iterator.hasNext()) {
      if (iterator.next().equals(e)) {
        return index; // Return the index if the element is found.
      }
      index++;
    }

    // If the element is not found, return -1.
    return -1;
  }

  @Override /** Return the index of the last matching element in 
   *  this list. Return -1 if no match. */
  public int lastIndexOf(E e) {
    //This statement is no longer needed: System.out.println("Implementation for assignment, part 4");

    // Use iterator to traverse the linkedlist.
    LinkedListIterator iterator = new LinkedListIterator();
    int lastIndex = -1;
    int index = 0;

    // Check linkedlist for the specified element.
    while (iterator.hasNext()) {
      if (iterator.next().equals(e)) {
        lastIndex = index; // Update the lastIndex if the element is found.
      }
      index++;
    }

    // Return the last index found (or -1 if the element is not found).
    return lastIndex;
  }

  @Override /** Replace the element at the specified position 
   *  in this list with the specified element. Return the old element. */
  public E set(int index, E e) {
    //This statement is no longer needed: System.out.println("Implementation for assignment, part 5");

    // Check if the index is within bounds.
    checkIndex(index);

    // Use iterator to traverse the list to the specified index.
    LinkedListIterator iterator = new LinkedListIterator();
    Node<E> current = iterator.current;

    for (int i = 0; i < index; i++) {
      current = current.next;
    }

    // Save the old element to return later.
    E oldElement = current.element;

    // Set the new element at the specified index.
    current.element = e;

    return oldElement;
  }

  @Override /** Reverses this list's elements in place.
   For the linked list, this is done by changing the direction of the node links.*/
  public void reverse() {
    //This statement is no longer needed: System.out.println("Implementation for assignment, part 6");

    // No processing needed if the list is empty or has only 1 node.
    if (head == null || head.next == null) {
      return;
    }

    // Keep references to the old head and tail nodes.
    Node<E> oldHead = head;
    Node<E> oldTail = tail;

    // References to three nodes: one, two, three.
    Node<E> one = head;
    Node<E> two = one.next;
    Node<E> three = two.next;

    // Iterate through the list and reverse the links.
    while (three != null) {
      // Reverse the link between two and one.
      two.next = one;

      // Move one, two, and three one position to the right.
      one = two;
      two = three;
      three = three.next;
    }

    // Link node two's field next to node one (last connection in the loop).
    two.next = one;

    // Set the new head and tail nodes.
    head = two;
    tail = oldHead;

    // Set the new tail's next field to null.
    tail.next = null;
  }

  private void checkIndex(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
  }

  @Override /** Override iterator() defined in Iterable */
  public java.util.Iterator<E> iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements java.util.Iterator<E> {
    private Node<E> current = MyLinkedList.this.head; // Node of the next element in the iteration
    
    @Override
    public boolean hasNext() {
       return (this.current != null);
    }

    @Override
    public E next() {
      if (!hasNext())
    	throw new NoSuchElementException();
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() {
      System.out.println("Implementation left as OPTIONAL exercise (7)");
    }
  }
  
  private static class Node<E> {
    E element;
    Node<E> next;

    public Node(E element) {
      this.element = element;
    }
  }
}
