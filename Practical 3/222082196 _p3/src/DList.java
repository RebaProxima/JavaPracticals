
public class DList<T extends Comparable<T>> implements IList<T>, Cloneable {

	private Node<T> header = null;
	private Node<T> trailer = null;
	private Integer size = 0;
	
	/**
	 * Default constructor
	 */
	public DList() {
		trailer = new Node<T>(null, null, null);
		header = new Node<T>(trailer, null, null);
		trailer.setPrev(header);
		size = 0;
	}
	
	/**
	 * Construct a List from an Array
	 * @param fromArray the array used to construct the list
	 */
	public DList(T[] fromArray) {
		//COMPLETE CODE HERE
		header = new Node<T>(null, null, null);
		trailer = new Node<T>(null, null, header);
		header.setNext(trailer);
		size=0;
		
		for(T items : fromArray) {
			addLast(items);
		}
	}
	
	/**
	 * Convert the list to an array.
	 */
	public T[] toArray() {
		//COMPLETE CODE HERE
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		
		Node<T> currentNode = header.getNext();
		int i = 0;
		
		while(currentNode == trailer) {
			array[i++] = currentNode.getElement();
			currentNode = currentNode.getNext();
		}
		
		return array;
	}
	
	/**
	 * Provide a deep copy of the Linked List
	 */
	@Override
	public DList<T> clone() {
		//COMPLETE CODE HERE
		DList<T> result = new DList<>();
		
		Node<T> currentNode = header.getNext();
		
		while(currentNode != trailer) {
			result.addLast(currentNode.getElement());
			currentNode = currentNode.getNext();
		}
		
		return result;
	}
	
	/**
	 * Add an element after a given node in the list
	 */
	@Override
	public Node<T> addAfter(Node<T> elem, T item) {
		//COMPLETE CODE HERE
		//Assume we have header - A - B - D - trailer
		// we want to insert after B
		Node<T> newNode = new Node<>(elem.getNext(), item ,elem);
		
		//Add the next Reference
		elem.getNext().setNext(newNode);
		//Add the reference to previous node
		elem.setPrev(newNode);
		
		size++;
		
		return newNode;
	}

	/**
	 * Add an element before a given node in a list
	 */
	@Override
	public Node<T> addBefore(Node<T> elem, T item) {
		//COMPLETE CODE HERE
		
		Node<T> newNode = new Node<>(elem,  item,elem.getPrev());
		
		elem.getPrev().setNext(newNode);
		elem.setNext(newNode);
		
		size++;
		
		return newNode;
	}

	/**
	 * Add an element to the start of the list
	 */
	public Node<T> addFirst(T item) {
		return addAfter(header, item);
	}
	
	/**
	 * Add an element to the end of the list
	 */
	public Node<T> addLast(T item) {
		return addBefore(trailer, item);
	}
	
	/**
	 * Remove a specified node from the list. The removed element is returned
	 */
	@Override
	public T remove(Node<T> elem) {
		//COMPLETE CODE HERE
		if(elem == header || elem == trailer) {
			return null;
		}
		//A -> B -> C, make sure that B is no longer able to connect with them
		elem.getPrev().setNext(elem.getNext());
		elem.getNext().setPrev(elem.getPrev());
		size--;
		return elem.getElement();
		
	}

	/**
	 * Returns the node that contains the element that is specified as a parameter
	 */
	@Override
	public Node<T> search(T elem) {
		
		Node<T> currentNode = header.getNext();
		
		while(currentNode != trailer) {
			
			if(currentNode.getElement().compareTo(elem) == 0) {
				
				return currentNode;
			}
			currentNode = currentNode.getNext();
		}
		
		return null;
	}

	/**
	 * Returns true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return (header.getNext() == trailer);
	}

	/**
	 * Return the size of the list
	 */
	@Override
	public Integer size() {
		return size;
	}
	
		/**
	 * Return the first element in the list
	 */
	public T head() {
		return header.getNext().getElement();
	}
	
	/**
	 * Returns a list that contains everything except the first element
	 */
	public IList<T> tail() {
		//COMPLETE CODE HERE
		DList<T> result = new DList<>();
		Node<T> currentNode = header.getNext().getNext();
		
		while(currentNode != trailer) {
			
			result.addLast(currentNode.getElement());
			currentNode = currentNode.getNext();
		}
		
		return result;
	}
	
	
	@Override
	public String toString() {
		String result = header.toString() + " <-> ";
		Node<T> currentNode = header.getNext();
			
		//COMPLETE CODE HERE
		while(currentNode != trailer) {
			result += currentNode.toString() + "<->";
			currentNode = currentNode.getNext();
		}
		
		result += trailer.toString();
		return result;
	}
	
	/**
	 * Return a new list that contains all the element in the current list
	 * that are less than a specified element
	 */
	public DList<T> splitLess(T element) {
		//COMPLETE CODE HERE
		DList<T> result = new DList<>();
		
		Node<T> currentNode = header.getNext();
		
		while(currentNode != trailer) {
			if(currentNode.getElement().compareTo(element) < 0 ) {
				result.addLast(currentNode.getElement());
			}
			
			currentNode = currentNode.getNext();
		}
		return result;
	}
	
	/**
	 * Return a new list that contains all the element in the current list
	 * that are greater than a specified element
	 */
	public DList<T> splitGreater(T element) {
		//COMPLETE CODE HERE
        DList<T> result = new DList<>();
		
		Node<T> currentNode = header.getNext();
		
		while(currentNode != trailer) {
			if(currentNode.getElement().compareTo(element) > 0 ) {
				result.addLast(currentNode.getElement());
			}
			
			currentNode = currentNode.getNext();
		}
		return result; 
	}
	
	/**
	 * Return a new list that contains all the element in the current list
	 * that are equal to a specified element
	 */
	public DList<T> splitEqual(T element) {
		//COMPLETE CODE HERE
        DList<T> result = new DList<>();
		
		Node<T> currentNode = header.getNext();
		
		while(currentNode != trailer) {
			if(currentNode.getElement().compareTo(element) == 0 ) {
				result.addLast(currentNode.getElement());
			}
			
			currentNode = currentNode.getNext();
		}
		return result;
	}
	
	/**
	 * Return a new IList that contains the elements merged from the current list
	 * and the passed otherList
	 * @param otherList the other list to merge
	 * @return a new list of element
	 */
	public DList<T> merge(DList<T> otherList) {
		DList<T> newList = new DList<T>();
		Node<T> currentNode = header.getNext();
		Node<T> currentNode2 = otherList.header.getNext();
		
		//COMPLETE CODE HERE
		while(currentNode != trailer.getNext() && currentNode2 != trailer.getNext()) {
			if(currentNode.getElement().compareTo(currentNode2.getElement()) <= 0){
				newList.addLast(currentNode.getElement());
				currentNode = currentNode.getNext();
			}else {
				newList.addLast(currentNode2.getElement());
				currentNode2 = currentNode2.getNext();
			}
			
		}
		
		while(currentNode != this.trailer) {
			newList.addLast(currentNode.getElement());
			currentNode = currentNode.getNext();
		}
		
		while(currentNode2 != otherList.trailer) {
			newList.addLast(currentNode2.getElement());
			currentNode2 = currentNode.getNext();
		}
		
		return newList;
	}
	
	/**
	 * Return a new list that has been sorted using a quick sort.
	 * @return a sorted list
	 */
	public DList<T> quicksort() {
		if (size() <= 1)
			return this.clone();
		
		T pivot = header.getNext().getElement();
		
		DList<T> smaller = splitLess(pivot).quicksort();
		
		DList<T> equal = splitEqual(pivot);
		
		DList<T> greater = splitGreater(pivot).quicksort();
	
		//merge everything together
		DList<T> sortedList = smaller.merge(equal).merge(greater);
		return sortedList;
	}
	
}
