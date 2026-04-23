import java.nio.ByteBuffer;
import java.util.Iterator;
//Overall Hash Table: 40 marks ***********************************************

public class HashTable<K,V> implements IMap<K,V> {
	Object[] table;
	int size;
	int capacity;

	/**
	 * Default constructor
	 */
	public HashTable() {
		this(100);
	}
	
	/**
	 * Constructor - provides the size of the array
	 * @param initialSize the initial size
	 */
	public HashTable(int initialSize) {
		this.capacity = initialSize;
		this.table = createArray(this.capacity);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * Create an array that contains the positionslists that act as buckets
	 * @param size the size of the array to create
	 * @return the array that was created
	 * 4 marks ***********************************************
	 */
	private Object[] createArray(int size) {
		//TODO: complete
		Object[] array = new Object[size];
		
		for(int i = 0; i < size ; i++) {
			array[i] = new PositionList<Entry<K,V>>();
		}
		
		return array;
	}
	
	/**
	 * Hash a string input
	 * @param str The input string
	 * @return the hash code for the integer
	 */
	private long hash(String str) {
		return hash(str.getBytes());
	}
	
	/**
	 * A hash an integer input
	 * @param inputInt the input input
	 * @return the hash code for the integer
	 */
	private long hash(int inputInt) {
		byte[] bytes = ByteBuffer.allocate(4).putInt(inputInt).array();
		return hash(bytes);
	}
	
	/**
	 * Calculate a hash code using the djb2 hash function
	 * This hash function was created by Dan Bernstein, however
	 * normally it works with string inputs, this has been modified
	 * to work with byte inputs
	 * @param input the input array of bytes
	 * @return a hash value for the input
	 */
	private long hash(byte[] input) {
		long hash = 5381;
		for (int i = 0; i < input.length; i++) {
			hash = ((hash << 5) + hash) + input[i];
		}
		return hash;
	}
	
	/**
	 * Calculate a hash for either a string or an Integer
	 * @param item the item to hash
	 * @return a compressed hash code for the item
	 */
	private long hash(K item) {
		if (item instanceof Integer) {
			return hash((Integer)item) % capacity;
		}
		
		if (item instanceof String) {
			return hash((String)item) % capacity;
		}
		
		return (long)item.hashCode() % capacity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Remove an item from the hash table
	 * @param key the key of the item to remove
	 * 10 marks ***********************************************
	 */
	public V remove(K key) {
		//TODO: complete
		//Change the key to the integer
		int index = (int) Math.abs(hash(key));
		
		//Get an list
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>) table[index];
		
		//Create an iterator
		Iterator<Entry<K,V>> iterator = bucket.iterator();
		
		//Check if the entry exist
		while(iterator.hasNext()) {
			Entry<K,V> entry = iterator.next();
			
			if(entry.getKey().equals(key)) {
				V val = entry.getValue();
				iterator.remove();
				size--;
				
				return val;
			}
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Get the value for a given key
	 * @param key the key for the item
	 * @returns the value for the associated key
	 * 10 marks ***********************************************
	 */
	public V get(K key) {
		//TODO: complete
		//Change the key to the integer
		int index = (int) Math.abs(hash(key));
				
		//Get an list
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>) table[index];
				
		//Create an iterator
		Iterator<Entry<K,V>> iterator = bucket.iterator();
				
		//Check if the entry exist
		while(iterator.hasNext()) {
			Entry<K,V> entry = iterator.next();
					
			if(entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
				
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Put an item into the hash table
	 * @param key the key for the item (unique)
	 * @param value the value for the item
	 * 8 marks ***********************************************
	 */
	public void put(K key, V value) {
		//TODO: complete
		//Get the index
		int index = (int) Math.abs(hash(key));
		
		//Get the bucketList
		PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>) table[index];
		
		//Create the itarator
		Iterator<Entry<K,V>> iterator = bucket.iterator();
		
		//Check if the key exist
		while(iterator.hasNext()) {
			Entry<K,V> entry = iterator.next();
			
			if(entry.getKey().equals(key)) {
				entry.setValue(value);
			}
		}
		
		bucket.addLast(new Entry<>(key,value));
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Returns an iterator over the keys of the hash table
	 * 8 marks ***********************************************
	 */
	public Iterator<K> keys() {
		//TODO: complete
		PositionList<K> key = new PositionList<K>();
		
		for(int i = 0; i < table.length; i++) {
			
			PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>) table[i];
			
			Iterator<Entry<K,V>> iterator = bucket.iterator();
			
			while(iterator.hasNext()) {
				
				Entry<K,V> entry = iterator.next();
				key.addLast(entry.getKey());
				
			}
		}
		
		return key.iterator();
	}

	@Override
	/**
	 * Returns an iterator over the values in the hash table
	 */
	public Iterator<V> values() {
		PositionList<V> val = new PositionList<V>();
		for (int i = 0; i < table.length; i++) {
			PositionList<Entry<K,V>> bucket = (PositionList<Entry<K,V>>)table[i];
			Iterator<Entry<K,V>> bucketIterator = bucket.iterator();
			while (bucketIterator.hasNext()) {
				Entry<K,V> item = bucketIterator.next();
				val.addLast(item.getValue());
			}
		}
		
		return val.iterator();
	}

	@Override
	/**
	 * Returns the size of the hashtable
	 */
	public int size() {
		return this.size;
	}

	@Override
	/**
	 * Returns true if the hashtable is empty;
	 */
	public boolean isEmpty() {
		return size == 0;
	}

}
