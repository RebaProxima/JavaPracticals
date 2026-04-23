/*
 * Total Marks SList Class: 26 Marks
 */
public class SList<T extends Comparable<T>> implements IList<T> {

	private Node<T> head = null;
	private Node<T> tail = null;
	private Integer size = 0;
	
	/**
	 * Default constructor
	 */
	public SList() {}
	
	/**
	 * returns the first node in the list.
	 */
	@Override
	public Node<T> first() {
		return head;
	}
	
	/**
	 * returns the last node in the list.
	 */
	@Override
	public Node<T> last() {
		return tail;
	}
	
	/**
	 * returns the node before a given node in the list.
	 * 3 marks
	 */
	@Override
	public Node<T> prev(Node<T> node) {
		//TODO: Complete
		//Check if the node or head are null and  check if the current node is the head node, because if it is then no previous
		if(node == null || head == null || node == head) {
			return null;
		}
		
		Node<T> currentNode = head;
		
		while(currentNode != null && currentNode.getNext() != null) {
			if ( currentNode.getNext() != node ) {
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		return null;
	}
	
	/**
	 * returns the next node after a given node in the list.
	 */
	@Override
	public Node<T> next(Node<T> node) {
		return node.getNext();
	}
	
	/**
	 * Replace the element of a given node in the list
	 * @return the old element of the given node
	 * 1 Marks
	 */
	@Override
	public T replace(Node<T> node, T item) {
		//TODO: Complete
		//If the node is empty
		if(node == null) {
			return null;
		}
		
		//Remove the old element from node
		T olditem = node.getElement();
		node.setElement(item);
		return olditem;
	}
	
	/**
	 * Add an element after a given node in the list
	 * 3 Marks
	 */
	@Override
	public Node<T> insertAfter(Node<T> node, T item) {
		//TODO: Complete
		// Lets assume that X is the node we want to add Before
		if(node == null) {
			return null;
		}
		
		//Create a new node and update it
		Node<T> newNode = new Node<>(node, item);
		node.setNext(newNode);
		
		//If it happens that the node will be added after  the tail
		if(node == tail) {
			tail = newNode;
		}
		//Increase the size
		size++;
		
		return newNode;
	}

	/**
	 * Add an element before a given node in the list
	 * 5 Marks
	 */
	@Override
	public Node<T> insertBefore(Node<T> node, T item) {
		//TODO: Complete
		
		// Let X be a mode to add Before other Nodes
		// We have A -> B -> C -> D 
		
		// If X is null Node has nothing
		if(node == null) {
			return null;
		}
		if(node == head) {
			//We have to add it before the Head
			return insertFirst(item);
		}
		
		Node<T> previousNode = prev(node);
		if(previousNode == null) {
			return null;
		}
		// X can be inserted 
		Node<T> newNode = new Node<>(node.getNext(), item);
		node.setNext(newNode);
		
		return newNode;
	}

	/**
	 * Add an element to the start of the listpreviousNode
	 * @return the new node
	 */
	@Override
	public Node<T> insertFirst(T item) {
		Node<T> newNode = new Node<T>(head, item);
		head = newNode;
		if (isEmpty())
			tail = head;
		size++;
		return newNode;	
	} 

	/**
	 * Add an element to the end of the list
	 * @return the new node
	 */
	@Override
	public Node<T> insertLast(T item){
		Node<T> newest = new Node<T>(null, item);
		if (isEmpty())
		  head = newest;
		else
		  tail.setNext(newest);
		tail = newest;
		size++;
		return newest;
	}
	
	/**
	 * Remove a specified node from the list. The removed element is returned
	 * 5 Marks
	 */
	@Override
	public T remove(Node<T> node) {		
		//TODO: Complete
		
		// Let X = Node that must be removed
 		// A -> B -> C -> D -> E
		
		// If X = 0 then we don't get anything
		// If the node we looking to replace is empty then we should get nothing
		if(node == null ) {
			return null;
		}
		
		//If X = A
		// We check if the node is the head or not
		if(node == head) {
			//We remove the element of the head
			T removedElementOfTheHead = head.getElement();
			// X != A then X = B
			//We have to update the next element to be the head
			head = head.getNext();
			//reduce size
			size--;
			
			// If there was only one Node
			if( size == 0) {
				return null;
			}
			return removedElementOfTheHead;
		}
		
		//If X is either B,C,D. what happens?
		//I can try to target the previous and skip the current
		Node<T> previousNode = prev(node);
		//Skip a Node, we can skip
		previousNode.setNext(node.getNext());
		
		// What if X = E, then D will be the tail
		if(node == tail) {
			tail = previousNode;
		}
		
		return node.getElement();
		
	}

	/**
	 * Returns the node that contains the element that is specified as a parameter
	 * 5 Marks
	 */
	@Override
	public Node<T> search(T elem) {
		//TODO: Complete
		// We let our Node be head
		Node<T> current = head;
		
		while(current != null) {
			if(current.getElement().compareTo(elem) == 0) {
				return current;
			}
			//update to another Node
			current = current.getNext();
		}
		
		return null;
	}

	/**
	 * Returns true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Return the size of the list
	 */
	@Override
	public Integer size() {
		return size;
	}
	
	/**
	 * The overridden method for displaying items in the Singly-Linked List
	 * format: <e1><-><e2><-><e3><->
	 * 4 Marks
	 */
	@Override
	public String toString() {
		String result = "";
		Node<T> currentNode = head;
		//TODO: Complete
		while(currentNode != null) {
			result += "<" + currentNode.getElement().toString() + "><->" ;
			// It should move to another Node
			currentNode = currentNode.getNext();
		}
		
		return result;
	}
}
