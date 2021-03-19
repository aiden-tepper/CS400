/*
 * Aiden Tepper
 * ajtepper@wisc.edu
 * Lecture 001
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
    
    private T ds;
    
    protected abstract T createInstance();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        ds = createInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        ds = null;
    }

    
    @Test
    void test00_empty_ds_size() {
        if (ds.size() != 0)
        fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    // TODO: review tests 01 - 04

    @Test
    void test01_insert_one() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.size()==1);
    }
    
    @Test
    void test02_insert_remove_one_size_0() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.remove(key)); // remove the key
        if (ds.size() != 0)
            fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    @Test
    void test03_duplicate_exception_thrown() {
        String key = "1";
        String value = "one";
        ds.insert("1", "one");
        ds.insert("2", "two");
        try { 
            ds.insert(key, value); 
            fail("duplicate exception not thrown");
        }
        catch (RuntimeException re) { }
        assert (ds.size()==2);
    }
            
    
    @Test
    void test04_remove_returns_false_when_key_not_present() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (!ds.remove("2")); // remove non-existent key is false
        assert (ds.remove(key));  // remove existing key is true
        if (ds.get(key)!=null)
            fail("get("+key+ ") returned " + ds.get(key) + " which should have been removed");
    }
    
    
    @Test
    void test05_insert_remove_one() {
    	String key = "1";
        String value = "one";
        ds.insert(key, value);
        ds.remove(key);
        assert (ds.get(key) == null);
    }
    
    
    @Test
    void test06_insert_many_size() {
    	ds.insert("1", "one");
        ds.insert("2", "two");
        ds.insert("3", "three");
        ds.insert("4", "four");
        assert (ds.size() == 4);
    }
    
    
    @Test
    void test07_duplicate_value() { // inserts two pairs with different keys, but same values; fails if second doesn't insert
    	ds.insert("1", "one");
    	try {
    		ds.insert("2", "one");
    	}
    	catch (Exception e) {
    		fail("unable to insert item with duplicate value");
    	}
    	assert (ds.get("2").equals("one"));
    }
    
    
    @Test
    void test08_contains() {
    	String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.contains(key));
    }
    
    
    @Test
    void test09_get() {
    	String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.get(key) == value);
    }
    
    @Test
    void test10_increase_capacity() {
    	ds.insert("1", "one");
        ds.insert("2", "two");
        ds.insert("3", "three");
        ds.insert("4", "four");
        ds.insert("5", "five");
        ds.insert("6", "six");
        assert (ds.size() == 6);
    }
    
    @Test
    void test11_large_size() {
    	for(int i = 0; i < 1000; i++) {
    		ds.insert("" + i, "value of " + i);
    	}
    	assert(ds.size() == 1000);
    	for(int i = 0; i < 1000; i++) {
    		ds.remove("" + i);
    	}
    	assert(ds.size() == 0);
    }
    
    @Test
    void test12_insert_after_remove() {
    	String key = "1";
        String value = "one";
        ds.insert(key, value);
        ds.remove(key);
        try {
            ds.insert(key, value);
    	}
    	catch (Exception e) {
    		fail("unable to insert item with duplicate value");
    	}
        assert(ds.get(key).equals(value));
    }


}
