package application;
/////////////////////////////////////////////////////////////////////////////////
//
//Title: Milk Weights
//Course: 400, Spring 20
//
//Author: Juan Rodriguez, Aiden Tepper
//Email: jcrodriguez3@wisc.edu , ajtepper@wisc.edu
//Lecturer's Name: Prof. Deppeler
////////////////////////////////////////////////////////////////////////////////
import application.MSHashTable.MilkSale;

public interface HashTableADT<K extends Comparable<K>, V>{
    // Inserts an element into the hash table
    public void insert(V value, K key, int weight);
    
    // Given a key searches the hash table, returns true iff element is found
    public boolean remove(K key);
    
    // Given a key, retrieves and returns the corresponding MilkSale object
    public MilkSale get(K key);

    // Returns the number of nodes currently in the hash table
    public int getNumKeys();

    // Calculates and returns the current load factor
    public double getLoadFactor();

    // Returns the current set capacity of the hash table
    public int getCapacity();

}
