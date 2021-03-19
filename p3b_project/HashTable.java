/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 * This project has to do with testing the performance of my hash table implementation against Java's TreeMap.
 * This file contains the HashTable class -- my hash table implementation.
 */

import java.util.ArrayList;

/**
 * This class represents a hash table and contains methods for adding, getting, and removing
 * from the hash table as well as retrieving various data points about the table itself such
 * as load factor and current capacity.
 * 
 * The collision resolution scheme I chose was a chained bucket -- ArrayList of linked nodes.
 * When a HashNode is added to an index that already contains a HashNode, it is added to the
 * beginning of the bucket and the pre-existing node is set to its 'next' value.
 * 
 * The hashing algorithm I used, seen in the private hash() method, is calling Java's built-
 * in hashCode() function on the given key and taking the modulo of that and the current
 * capacity of the hash table. It looks like this: key.hashCode() % hashTable.size().
 * 
 * @author Aiden Tepper
 *
 * @param <K> - generic key
 * @param <V> - generic value
 */
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {

	/**
	 * Inner class that represents a linked node in the hash table. Each node has a key, a
	 * corresponding value, and a a .next field for the next node in the bucket (if there is
	 * one).
	 * 
	 * @author Aiden Tepper
	 *
	 * @param <K> - generic key
	 * @param <V> - generic value
	 */
	private class HashNode<K, V> {

		public K key;
		public V value;
		public HashNode next;

		/**
		 * Constructor that assigns the given key and value to the pre-declared variables.
		 * 
		 * @param key
		 * @param value
		 */
		private HashNode(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		
		/**
		 * Returns a string representation of the bucket for testing purposes
		 * 
		 * @return string representation
		 */
		@Override
		public String toString() {
			String output = "";
			output += key;
			HashNode x = next;
			while(x != null) {
				output += " --> " + x.key;
				x = x.next;
			}
			return output;
		}

	}

	private ArrayList<HashNode<K, V>> hashTable;
	private int currentCapacity;
	private double loadFactorThreshold;
	private int currentSize;

	/**
	 * Default constructor, initializes private variables declared above to preset values
	 * and fills the hashTable ArrayList with null values.
	 * 
	 */
	public HashTable() {
		
		this.hashTable = new ArrayList<>();
		currentCapacity = 100;
		this.loadFactorThreshold = 0.75;
		currentSize = 0;
		
		//fill the ArrayList with null values
		for(int i = 0; i < currentCapacity; i++) {
			hashTable.add(null);
		}
		
	}

	/**
	 * Constructor similar to default constructor but initialCapacity and loadFactorThreshold
	 * are set by values passed into the constructor.
	 * 
	 * @param initialCapacity - the initial size of the ArrayList
	 * @param loadFactorThreshold - the load factor threshold for the hash table
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		
		this.hashTable = new ArrayList<>();
		currentCapacity = initialCapacity;
		this.loadFactorThreshold = loadFactorThreshold;
		currentSize = 0;
		
		//fill the ArrayList with null values
		for(int i = 0; i < initialCapacity; i++) {
			hashTable.add(null);
		}
		
	}


	/**
	 * Adds the key,value pair to the data structure and increase the number of keys.
	 * If the key is null, throw IllegalNullKeyException, and if the key is already in
	 * the data structure, replace value with new value. When the current load factor
	 * is greater than or equal to the specified load factor threshold, increaseSize()
	 * is called, resizing the hash table and rehashing all elements.
	 * 
	 * @param key - key of the node to be inserted
	 * @param value - value of the node to be inserted
	 * @throws IllegalNullKeyException - thrown when the key parameter is null
	 */
	public void insert(K key, V value) throws IllegalNullKeyException {

		if(key == null) {
			throw new IllegalNullKeyException("Key cannot be null.");
		}

		HashNode node = new HashNode(key, value);
		int index = hash(key);

		HashNode head = hashTable.get(index);

		//replace old value with new value if key already exists
		while(head != null) {
			if(head.key.equals(key)) { 
				head.value = value; 
				return; 
			}
			head = head.next;
		}

		head = hashTable.get(index);
		node.next = head;
		hashTable.set(index, node);
		currentSize++;

		//if load factor threshold is met or exceeded, call increaseSize() method
		if(getLoadFactor() >= loadFactorThreshold) {
			increaseSize();
		}
		
	}


	/**
	 * If the given key is found, removes the key,value pair from the data structure,
	 * decreases the number of keys, and returns true. If the key is null, throws
	 * IllegalNullKeyException. If the key is not found, return false.
	 * 
	 * @param key - key of the node to find and delete
	 * @throws IllegalNullKeyException - thrown when the key parameter is null
	 * @return true if key is removed, false if key is not found
	 */
	public boolean remove(K key) throws IllegalNullKeyException {

		if(key == null) {
			throw new IllegalNullKeyException("Key cannot be null.");
		}

		int index = hash(key);

		if(hashTable.get(index) == null) {
			return false;
		}

		HashNode head = hashTable.get(index);
		HashNode<K, V> previous = null; 

		//find the node with matching key
		while(head != null) {
			if (head.key.equals(key)) {
				break;
			}
			previous = head; 
			head = head.next; 
		}

		//remove the found node and adjust chained bucket
		if (previous != null) {
			previous.next = head.next;
		} else {
			hashTable.set(index, head.next);
		}

		currentSize--;
		return true;

	}


	/**
	 * Finds and returns the value associated with the given key. Does not remove
	 * the key from the hash table or decrease the number of keys. If the key is
	 * null, throws IllegalNullKeyException. If the key is not found, throws
	 * KeyNotFoundException.
	 * 
	 * @param key of the node to search for
	 * @throws IllegalNullKeyException - thrown when the key parameter is null
	 * @throws KeyNotFoundException - thrown if the key isn't found in the hash table
	 * @return the value associated with the key, if found
	 */
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {

		if(key == null) {
			throw new IllegalNullKeyException("Key cannot be null.");
		}

		int index = hash(key);	
		HashNode<K, V> head = hashTable.get(index);

		//find node with matching key
		while(head != null) { 
			if(head.key.equals(key)) {
				return head.value;
			}
			head = head.next;
		}

		throw new KeyNotFoundException("Key was not found in hash table.");

	}


	/**
	 * Returns the number of key,value pairs in the data structure.
	 * 
	 * @return the number of nodes
	 */
	public int numKeys() {
		return currentSize;
	}


	/**
	 * Returns the load factor threshold that was either set by the default constructor
	 * or passed into the other constructor when creating the instance of the HashTable.
	 * 
	 * @return the set load factor threshold
	 */
	public double getLoadFactorThreshold() {
		return loadFactorThreshold;
	}

	/**
	 * Calculates and returns the current load factor for this hash table using the equation
	 * load factor = number of items / current table size.
	 * 
	 * @return the current load factor
	 */
	public double getLoadFactor() {
		double loadFactor = currentSize/currentCapacity;
		return loadFactor;
	}


	/**
	 * Returns the current capacity of the hash table ArrayList.
	 * 
	 * @return the current capacity
	 */
	public int getCapacity() {
		return currentCapacity;
	}


	/**
	 * Returns the collision resolution scheme used for this hash table. For my implementation,
	 * I used a chained bucket -- array of linked nodes, so this method returns the integer 5.
	 * 
	 * @return the collision resolution scheme used (5)
	 */
	public int getCollisionResolution() {
		return 5;
	}



	/**
	 * Takes a key as a parameter and calculates the hash index by taking the modulo of the value
	 * of the Java hashCode() function (called on the key) by the current size of the hash table.
	 * 
	 * @param key to be hashed
	 * @return hash index for the key
	 */
	private int hash(K key) {
		int hashCode = key.hashCode();
		int hash = Math.abs(hashCode % hashTable.size());
		return hash;
	}

	/**
	 * Private helper method called by insert() when the load factor threshold is met
	 * or exceeded. Doubles the size of the ArrayList (fills with null values), adds 1
	 * to the capacity, then fully rehashes all elements of the original hash table.
	 * Once increased, the capacity never decreases.
	 * 
	 */
	private void increaseSize() {

		//copy hashTable and add currentCapacity*2 + 1 null values to original ArrayList
		ArrayList<HashNode<K, V>> temp = hashTable;
		hashTable = new ArrayList<HashNode<K, V>>(currentCapacity*2 + 1);
		for(int i = 0; i < currentCapacity*2 + 1; i++) {
			hashTable.add(null);
		}
		
		currentSize = 0;
		currentCapacity *= 2;
		currentCapacity ++;
		
		//go through old ArrayList and add each node to temporary ArrayList
		for (HashNode<K, V> head : temp) {
			while (head != null) {
				try {
					insert(head.key, head.value);
				} catch(Exception e) {
					System.out.println(e);
				}
				head = head.next; 
			}
		}
		
	}

}
