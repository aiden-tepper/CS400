/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 */

public class DS_My implements DataStructureADT< String, String > {

	
    // Inner class for storing key and value as a pair
	private class item {
		String key;
		String value;
		private item(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}

    // Private Fields of the class
	private item[] itemArray;
	private int size = 0;
    
    public DS_My() {
    	itemArray = new item[5];
    }

    // Add the key,value pair to the data structure and increases size.
    // If key is null, throws IllegalArgumentException("null key");
    // If key is already in data structure, throws RuntimeException("duplicate key");
    // can accept and insert null values
	public void insert(String key, String value) {
		if(key == null)
			throw new IllegalArgumentException("null key");
		for(int i = 0; i < size; i++) {
			if (itemArray[i].key.equals(key))
				throw new RuntimeException("duplicate key");
		}
		if(size == itemArray.length - 1)
			increaseCapacity();
		itemArray[size] = new item(key, value);
		size++;
	}

	// If key is found, Removes the key from the data structure and decreases size
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    // If key is not found, returns false.
	public boolean remove(String key) {
		if(key == null)
			throw new IllegalArgumentException("null key");
		for(int i = 0; i < size; i++) {
			if (itemArray[i].key.equals(key)) {
				itemArray[i] = null;
				shiftDown(i);
				size--;
				return true;
			}
		}
		return false;
	}

	// Returns the value associated with the specified key
    // get - does not remove key or decrease size
    // return null if key is not null and is not found in data structure
    // If key is null, throws IllegalArgumentException("null key")
	public String get(String key) {
		if(key == null)
			throw new IllegalArgumentException("null key");
		for(int i = 0; i < size; i++) {
			if(itemArray[i].key.equals(key)) {
				return itemArray[i].value;
			}
		}
		return null;
	}

	// Returns true if the key is in the data structure
    // Returns false if key is null or not present
	public boolean contains(String key) {
		for(int i = 0; i < size; i++) {
			if(itemArray[i].key.equals(key)) {
				return true;
			}
		}
		return false;
	}

	// Returns the number of elements in the data structure
	public int size() {
		return size;
	}

	// Helper method that doubles the size of the array when the capacity is reached
    private void increaseCapacity() {
    	int newSize = itemArray.length*2;
    	item[] tempArray = new item[itemArray.length*2];
    	for(int i = 0; i < itemArray.length; i++) {
    		tempArray[i] = itemArray[i];
    	}
    	itemArray = tempArray;
    }
    
    // Helper method that, when an item is removed, shifts all following items down 1 index
    private void shiftDown(int i) {
    	for(int a = i; a < size; a++) {
    		itemArray[a] = itemArray[a+1];
    	}
    }
	

}                            
    
