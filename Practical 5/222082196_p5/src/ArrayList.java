//************ Total Marks for ArrayList: 20 marks
import java.util.Iterator;

public class ArrayList<T> implements List<T>, Iterable<T> {

	private T[] array;
	private Integer size;
	private Integer arrayLength;
	private Integer strategy;
	
	/*
	 * The default constructor
	 */
	public ArrayList() {
		this(1);
	}
	
	/*
	 * The overloaded constructor for creating an ArrayList 
	 */
	public ArrayList(Integer strategy) {
		this.strategy = strategy;
		this.arrayLength = 1;
		this.array = createArray(this.arrayLength);
		this.size = 0;
	}
	
	/*
	 * A helper method for creating the underlying array
	 * ********** 5 marks ****************************
	 */
	@SuppressWarnings("unchecked")
	private T[] createArray(int size) {
		//TODO: Complete
		return (T[]) new Object[size];
	}
	
	/*
	 * The method for retrieving the element from the ArrayList
	 * @param the index to retrieve from
	 */
	@Override
	public T get(Integer i) {
		if (i >= size) {
			throw new ArrayListException("Index greater than size");
		}
		if (i < 0) {
			throw new ArrayListException("Index out of range");
		}
		return array[i];
	}

	/*
	 * The method for replacing an element in the ArrayList
	 * @param The index and he element 
	 */
	@Override
	public void set(Integer i, T e) {
		if (i >= size) {
			throw new ArrayListException("Index greater than size");
		}
		if (i < 0) {
			throw new ArrayListException("Index out of range");
		}
		
		array[i] = e;
	}

	/*
	 * The method for adding an element to the ArrayList
	 * @param the index for where the new element needs to be added and the element
	 * ********** 5 marks ****************************
	 */
	@Override
	public void add(Integer i, T e) {
		//TODO: Complete
		//Check if the index is in the range
		if(i < 0 || i > size) {
			throw new ArrayListException("index not in range");
		}
		
		//Expand the array if its full
		if( size == arrayLength) {
			expandArray();
		}
		
		//shift elements to the left
		shiftElementsRight(i);
		array[i] = e;
		size++;
	}

	/*
	 * The method for removing an element from the arrayList
	 * @param the index of the element for removal
	 * ********** 5 marks ****************************
	 */
	@Override
	public T remove(Integer i) {
		//TODO: Complete
		if(i < 0 || i > size) {
			throw new ArrayListException("index not in range");
		}
		
		T elementToRemove = array[i];
		shiftElementsLeft(i);
		size--;
		
		return elementToRemove;
	}

	/*
	 * The auxiliary method to determine the size of the ArrayList
	 */
	@Override
	public Integer size() {
		return size;
	}

	/*
	 * The auxiliary method to check if the list is empty 
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * The overridden toString method
	 */
	public String toString() {
		String str = "[";
		for (int i = 0; i < size-1; i++) {
			str += array[i].toString() + ",";
		}
		if (size > 0) {
			str += array[size-1];
		}
		str += "]";
		return str;
	}
	
	/*
	 * The expand array function that creates a new array that depends on the strategy 
	 * (1 for incremental and 2 for doubling) and copies the elements to the new array
	 * ********** 10 marks **************************** 
	 */
	private void expandArray() {
		//TODO: Complete
		// when its 1 its adding
		// when its 2 its doubling
		if(strategy == 1) {
			arrayLength = arrayLength + 1;
		} else if(strategy == 2) {
			arrayLength = arrayLength * 2;
		}else { 
			System.out.println("No strategy");
		}
		
		T[] newArray = createArray(arrayLength);
		
		for(int i = 0; i < size ; i++) {
			newArray[i] = array[i];
		}
		
		array = newArray;
	}
	
	/*
	 * A method for shifting all the elements up by one to the right
	 * @param the index from where to shift
	 */
	private void shiftElementsRight(Integer pos) {
		for (int i = this.size - 1; i >= pos; i--) {
			this.array[i+1] = this.array[i];
		}
	}
	
	/*
	 * A method for shifting all the elements up by one to the left
	 * @param the index from where to shift
	 */
	private void shiftElementsLeft(Integer pos) {
		for (int i = pos; i < size; i++) {
			this.array[i] = this.array[i+1];
		}
	}

	/*
	 * The overridden iterator method
	 * ********** 2 marks ****************************
	 */
	@Override
	public Iterator<T> iterator() {		
		//TODO: Complete
		return new ArrayListIterator<T>(this);
	}

}
