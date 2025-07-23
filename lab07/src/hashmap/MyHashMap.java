package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size = 0;
    private int initialCapacity = 16;
    private double loadFactor = 0.75;
    /** Constructors */
    public MyHashMap() {
        initialBuckets();
    }

    public MyHashMap(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        initialBuckets();
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        initialBuckets();
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }
    protected void initialBuckets() {
        // TODO: Fill in this method.
        buckets = new Collection[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) {
            buckets[i] = createBucket();
        }
    }
//    protected Collection<Node> createBucket() {
//        // TODO: Fill in this method.
//        return new HashSet<>();
//    }


    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = Math.floorMod(key.hashCode(), initialCapacity);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size++;
        double Occupancy = (double) size / initialCapacity;
        if(Occupancy >= loadFactor) {
            resizeBuckets();
        }
    }
    public void resizeBuckets() {
        int newCapacity = initialCapacity * 2;
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new LinkedList<>();
        }
        for(int i = 0; i < initialCapacity; i++) {
            for(Node node : buckets[i]) {
                int index = Math.floorMod(node.key.hashCode(), newCapacity);
                newBuckets[index].add(node);
            }
        }
        buckets = newBuckets;
        initialCapacity = newCapacity;
    }
    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), initialCapacity);
        if(buckets[index] == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        if(key == null) {
            return false;
        }
        int index = Math.floorMod(key.hashCode(), initialCapacity);
        if(buckets[index] == null) {
            return false;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        for(int i = 0; i < initialCapacity;i++) {
            buckets[i].clear();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for(int i = 0; i < initialCapacity;i++) {
            for(Node node : buckets[i]) {
                keys.add(node.key);
            }
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        int index = Math.floorMod(key.hashCode(), initialCapacity);
        if(buckets[index] == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                V value = node.value;
                buckets[index].remove(node);
                return value;

            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<K> {
        int index = 0;
        Iterator<Node> it = buckets[0].iterator();
        @Override
        public boolean hasNext() {
            return index < initialCapacity;
        }

        @Override
        public K next() {
            if(it.hasNext()) {
                return it.next().key;
            } else {
                while(!it.hasNext()) {
                    index++;
                }
                return it.next().key;
            }
        }
    }
}
