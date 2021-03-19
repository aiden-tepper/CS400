/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 * This project has to do with testing the performance of my hash table implementation against Java's TreeMap.
 * This file contains the HashTable class -- my hash table implementation.
 */

import java.util.TreeMap;

/**
 * This class declares and initializes a HashTable object and a TreeMap object. It contains
 * a method for both inserting and retrieving a key,value pair into/from both instances.
 * 
 * @author Aiden Tepper
 *
 * @param <K>
 * @param <V>
 */
public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	/**
	 * No-argument constructor initializes both the HashTable and TreeMap objects.
	 */
	public MyProfiler() {
		hashtable = new HashTable<>();
		treemap = new TreeMap<>();
	}

	/**
	 * Method that inserts a key,value pair into both instances.
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(K key, V value) {
		try {
			hashtable.insert(key, value);
			treemap.put(key, value);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Method that retrieves a key,value pair into both instances.
	 * 
	 * @param key
	 */
	public void retrieve(K key) {
		try {
			hashtable.get(key);
			treemap.get(key);
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Main method, declares/initializes a MyProfiler instance (<Integer, Integer>) then
	 * calls the insert and retrieve methods numElements amount of times. numElements is
	 * passed as an argument when calling the main method.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			
			int numElements = Integer.parseInt(args[0]);
			
			// Declare/initialize MyProfiler object
			MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();

			// Call insert method numElements times
			for(int i = 0; i < numElements; i++) {
				profile.insert(i, i);
			}
			
			// Call retrieve method numElements times
			for(int i = 0; i < numElements; i++) {
				profile.retrieve(i);
			}
			
			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
			
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}