/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 * This project has to do with implementing a hash table.
 * This file contains the HashTableTest class -- a number of JUnit tests that test
 * the implementation of the HashTable test.
 */

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains 14 methods that test the functionality of my hash
 * table implementation using JUnit testing.
 * 
 * @author Aiden Tepper
 *
 */
public class HashTableTest{

	HashTable<Integer, Integer> table;
    
    @Before
    public void setUp() throws Exception {
    	table = new HashTable<>();
    }

    @After
    public void tearDown() throws Exception {
    	table = null;
    }
    
    /** 
     * Tests that a HashTable returns an integer code
     * indicating which collision resolution strategy 
     * is used.
     * REFER TO HashTableADT for valid collision scheme codes.
     */
    @Test
    public void test000_collision_scheme() {
        HashTableADT htIntegerKey = new HashTable<Integer,String>();
        int scheme = htIntegerKey.getCollisionResolution();
        if (scheme < 1 || scheme > 9) 
            fail("collision resolution must be indicated with 1-9");
    }
        
    /** IMPLEMENTED AS EXAMPLE FOR YOU
     * Tests that insert(null,null) throws IllegalNullKeyException
     */
    @Test
    public void test001_IllegalNullKey() {
        try {
            HashTableADT htIntegerKey = new HashTable<Integer,String>();
            htIntegerKey.insert(null, null);
            fail("should not be able to insert null key");
        } 
        catch (IllegalNullKeyException e) { /* expected */ } 
        catch (Exception e) {
            fail("insert null key should not throw exception "+e.getClass().getName());
        }
    }
    
    /**
     * Tests that the default constructor works properly.
     */
    @Test
    public void test002_default_constructor() {
    	HashTable<Integer, Integer> table = new HashTable<>();
    	assert(table.getCapacity() == 100);
    	assert(table.getLoadFactorThreshold() == 0.75);
    	assert(table.numKeys() == 0);
    }
    
    /**
     * Tests that the second constructor works properly.
     */
    @Test
    public void test003_second_constructor() {
    	HashTable<Integer, Integer> table = new HashTable<>(50, 0.6);
    	assert(table.getCapacity() == 50);
    	assert(table.getLoadFactorThreshold() == 0.6);
    	assert(table.numKeys() == 0);
    }
    
    /**
     * Tests that inserting 10 values increases the current size to 10.
     */
    @Test
    public void test004_insert_10() {
    	try {
    		table.insert(1, 6);
    		table.insert(2, 6);
    		table.insert(3, 6);
    		table.insert(4, 6);
    		table.insert(5, 6);
    		table.insert(6, 6);
    		table.insert(7, 6);
    		table.insert(8, 6);
    		table.insert(9, 6);
    		table.insert(10, 6);
    	} catch(Exception e) {
    		fail("no exception should be thrown");
    	}
    	assert(table.numKeys() == 10);
    }
    
    /**
     * Tests functionality of adding 10 different key,value pairs with the same hash index
     */
    @Test
    public void test005_chained_bucket() {
    	try {
    		table.insert(1, 6);
    		table.insert(101, 6);
    		table.insert(201, 6);
    		table.insert(301, 6);
    		table.insert(401, 6);
    		table.insert(501, 6);
    		table.insert(601, 6);
    		table.insert(701, 6);
    		table.insert(801, 6);
    		table.insert(901, 6);
    		assert(table.numKeys() == 10);
        	assert(table.get(301) == 6);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests the functionality of the remove method. Inserts 3 keys then removes
     * one, checking if numKeys decreases and that the key is actually removed.
     */
    @Test
    public void test006_remove() {
    	try {
    		table.insert(1, 6);
    		table.insert(2, 6);
    		table.insert(3, 6);
    		assert(table.numKeys() == 3);
    		table.remove(2);
    		assert(table.numKeys() == 2);
    		assert(table.remove(2) == false);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests that if the get method is called on a key that doesn't exist,
     * KeyNotFoundException will be thrown.
     */
    @Test
    public void test007_key_not_found() {
    	try {
    		table.insert(1, 6);
    		table.insert(2, 6);
    		table.insert(3, 6);
    		table.get(10);
    		fail("function should throw KeyNotFoundException");
    	} catch(KeyNotFoundException e) { //expected
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no other exception should be thrown");
    	}
    }
    
    /**
     * Tests that the get method returns the correct value and does not decrease
     * the number of keys.
     */
    @Test
    public void test008_get() {
    	try {
    		table.insert(1, 6);
    		table.insert(2, 6);
    		table.insert(3, 6);
    		assert(table.get(3) == 6);
    		assert(table.numKeys() == 3);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests the functionality of the numKeys method.
     */
    @Test
    public void test009_num_keys() {
    	try {
    		for(int i = 0; i < 50; i++) {
    			table.insert(i, i*2);
    		}
    		assert(table.numKeys() == 50);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests the functionality of the getLoadFactor method by seeing if load
     * factor is calculated correctly.
     */
    @Test
    public void test010_load_factor() {
    	try {
    		for(int i = 0; i < 50; i++) {
    			table.insert(i, i*2);
    		}
    		assert(table.getLoadFactor() == 2/3);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests that when the load factor is met or exceeded, the hash table is
     * properly resized and rehashed.
     */
    @Test
    public void test011_resize_rehash() {
    	try {
    		for(int i = 0; i < 100; i++) {
    			table.insert(i, i*2);
    		}
    		assert(table.getCapacity() == 201);
    		assert(table.get(67) == 67*2);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests that everything works when inserting a large amount of elements
     */
    @Test
    public void test012_large_insert() {
    	try {
    		for(int i = 0; i < 10000; i++) {
    			table.insert(i, i);
    		}
    		assert(table.getCapacity() == 12927);
    		assert(table.numKeys() == 10000);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
    /**
     * Tests that everything works when removing a large amount of elements
     */
    @Test
    public void test013_large_remove() {
    	try {
    		for(int i = 0; i < 10000; i++) {
    			table.insert(i, i);
    		}
    		for(int i = 0; i < 10000; i+=2) {
    			table.remove(i);
    		}
    		assert(table.numKeys() == 5000);
    	} catch(Exception e) {
    		System.out.println(e);
    		fail("no exception should be thrown");
    	}
    }
    
}
