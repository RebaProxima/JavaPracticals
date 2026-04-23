
public interface IList<T extends Comparable<T>> {
	public Node<T> insertBefore(Node<T> p, T item);
	public Node<T> insertAfter(Node<T> p, T item);
	public Node<T> insertFirst(T item);
	public Node<T> insertLast(T item);
	public Node<T> search(T p);
	
	public T remove(Node<T> p);
	public T replace(Node<T> p, T item);
	
	public Integer size();
	public boolean isEmpty();
	
	public Node<T> first();
	public Node<T> last();
	public Node<T> prev(Node<T> node);
	public Node<T> next(Node<T> node);
	


}
