/////////////////////////////////////////////////////////////////////////////////
//
//Title: Milk Weights
//Course: 400, Spring 20
//
//Author: Juan Rodriguez, Aiden Tepper
//Email: jcrodriguez3@wisc.edu , ajtepper@wisc.edu
//Lecturer's Name: Prof. Deppeler
//
////////////////////////////////////////////////////////////////////////////////

// This class uses a hash table with chained buckets to handle collisions.
package application;

public class MSHashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
    //Represents a milk sale to be added to the hash table
    public class MilkSale <K extends Comparable<K>, V> {
        
        public V date; //date sale occured
        public K farmID; //farm's unique id
        public int weight;
        public MilkSale next;

        private MilkSale(V date, K farmID, int weight) {
            this.date = date;
            this.farmID = farmID;
            this.weight = weight;
        }

       private void setNext(MilkSale next) {
            this.next = next;
        }

    }

    private double loadFactorThreshold;
    private int capacity;
    private int numKey;
    MilkSale[] ht;

    public MSHashTable() {
        ht = new MilkSale[12];
        loadFactorThreshold = 0.75;
        capacity = 12;
    }

    // Resizes and rehashes the HashTable
    private void resize() {
        capacity = capacity * 2 + 1;
        MilkSale[] newHt = new MilkSale[capacity]; // new hashtable array
        for (MilkSale elem : ht) {
            MilkSale curr = elem;
            while (curr != null) { // iterates through possible chained buckets and rehashes each
                                   // key
                int index = hashF((K) curr.farmID);
                if (newHt[index] != null) {
                    newHt[index] = curr;
                } else {
                    MilkSale runner = newHt[index];
                    while (runner != null) {
                        if (runner.next == null) {
                            runner.setNext(curr);
                        }
                    }
                }
                MilkSale next = curr.next;
                curr.setNext(null);
                curr = next;
            }
        }
        ht = newHt;
    }

    // Returns hashIndex for a specific key
    private int hashF(K key) {
        return key.hashCode() % capacity;
    }

    // Adds a new MilkSale to the hash table, sets next for a element if need be. Calls resize() if
    // loadFactorThreshold has been reached.
    @Override
    public void insert(V value, K farmID, int weight) {
        numKey++;
        if (numKey / capacity > loadFactorThreshold) {
            resize();
        }
        MilkSale elem = new MilkSale(value, farmID, weight);
        int index = hashF(farmID);
        if (ht[index] == null)
            ht[index] = elem;
        else {
            elem.setNext(ht[index]);
            ht[index] = elem;
        }
    }

    /**
     * Removes a KeyV form the hash table, setting a new head to a linked list bucket if needed.
     * @return true - item has successfully been removed
     *         false - item could not be found 
     */
    @Override
    public boolean remove(K farmID) {
        int index = hashF(farmID);
        MilkSale current = ht[index];
        MilkSale previous = ht[index];
        while (current != null) { // iterates through linked list at a specific index in the hash
                                  // table
            if (current.farmID.equals(farmID)) {
                if (previous != null) {
                    previous.setNext(current.next); // handles removal of elements that are not the
                                                    // head of linked list
                }
                ht[index] = current.next;
                numKey--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }


    /**
     * Given a key uses a hash index to find its hash code to and return the respective element.
     * @return value for the respective key given
     */
    @Override
    public MilkSale get(K key) {
        int index =  hashF(key);
        MilkSale current = ht[index];
        while (current != null) { // iterates through bucket, if need be
            if (current.farmID.equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

  //Returns number of keys in hash table
    @Override
    public int getNumKeys() {
        return numKey;
    }

    // Returns the current load factor of hash table
    @Override
    public double getLoadFactor() {
        return numKey / capacity;
    }

 // Returns max capacity of hash table
    @Override
    public int getCapacity() {
        return capacity;
    }

}
