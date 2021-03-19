import java.util.List;

/**
 * Implements a generic Red-Black tree.
 *
 * DO NOT CHANGE THE METHOD SIGNATURES OF PUBLIC METHODS
 * DO NOT ADD ANY PACKAGE LEVEL OR PUBLIC ACCESS METHODS OR FIELDS.
 * 
 * You are not required to override remove.
 * If you do not override this operation,
 * you may still have the method in your type, 
 * and have the method throw new UnsupportedOperationException.
 * See https://docs.oracle.com/javase/7/docs/api/java/lang/UnsupportedOperationException.html
 * 
 * @param <K> is the generic type of key, must be a Comparable tyle
 * @param <V> is the generic type of value
 */
public class RBT<K extends Comparable<K>, V> implements STADT<K,V>{

    // USE AND DO NOT EDIT THESE CONSTANTS
    public static final int RED = 0;
    public static final int BLACK = 1;


    // TODO: define a default no-arg constructor
    public RBT() {
    }

    /**
     * Returns the color of the node that contains the specified key.
     * Returns RBT.RED if the node is red, and RBT.BLACK if the node is black.
     * Returns -1 if the node is not found.
     * @param 
     * @return
     */
    public int colorOf(K key) {
        // TODO: implement private helper method as you see fit
        // From Deb's solution
        //Node found = getNodeWith(root,key); // TODO Auto-generated method stub
        //return found==null ? -1 : found.color;
        return -1;
    }

    /**
     * Returns true if root is null or the color of the root is black.
     * If root is null, returns true.
     * @return true if root is black, else returns false (for red)
     */
    public boolean rootIsBlack() {
        // TODO implement this method for your RBT 
        return false;
    }

    /**
     * Returns true if the node that contains this key is RED.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is RED,
     * else return false if key is found and the node's color is BLACK.
     */
    public boolean isRed(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
        return false;
    }

    /**
     * Returns true if the node that contains this key is BLACK.
     * If key is null, throws IllegalNullKeyException.
     * If key is not found, throws KeyNotFoundException.
     * @return true if the key is found and the node's color is BLACK,
     * else return false if key is found and the node's color is RED.
     */
    public boolean isBlack(K key) 
            throws IllegalNullKeyException, KeyNotFoundException {
        return false;
    }

    @Override
    public K getKeyAtRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getHeight() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<K> getInOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getPreOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getPostOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<K> getLevelOrderTraversal() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean remove(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean contains(K key) throws IllegalNullKeyException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int numKeys() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void print() {
        // TODO Auto-generated method stub
        
    }


    // TODO: override the insert method so that it rebalances 
    //       according to red-black tree insert algorithm.


    // TODO [OPTIONAL] you may override print() to include
    //      color R-red or B-black.
    
}
