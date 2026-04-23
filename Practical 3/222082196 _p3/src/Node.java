
public class Node<T> {
	//COMPLETE CODE HERE
	private T element;
	private Node<T> next;
	private Node<T> prev;
	
	/**
	 * Create an object of the node
	 * 
	 * @param e An element of the Node
	 * @param n Next reference
	 * @param p Previous reference
	 */
	public Node( Node<T> n , T e , Node<T> p) {
		
		next = n;
		element = e;
		prev = p;
	}
	
	/**
	 * 
	 * @return get the element of the Node
	 */
	
	public T getElement() {
		return element;
	}

	/**
	 * 
	 * @return Get the next reference
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * 
	 * @return Get the previous reference
	 */
	
	public Node<T> getPrev() {
		return prev;
	}
	
	/**
	 * @param next Set the next reference
	 */

	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * 
	 * @param prev Set the previous reference
	 */
	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}
	
	/**
	 * Returns in a string format
	 */
	public String toString() {
		
		if(element == null) {
			return "<>";
		}
		
		return "<" + element.toString() + ">";
	}
	
}
