package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.util.Dictionary;

/**

 */
public class MyHashTable<K,V>
        extends Dictionary<K,V>
        implements Map<K,V>, Cloneable, java.io.Serializable {

    private double loadFactor;
    private int size;

    public MyHashTable() {
        this.loadFactor = 0.75; //the default load factor is 0.75
        this.size = 11;         //the default capacity of the hash table is 11
    }

    public MyHashTable(int initSize) {
        this.size = initSize;  //default load factor is 0.75 with the initial capacity is initSize
    }

    public MyHashTable(int initSize, double ratio) {
        this.size = initSize;
        this.loadFactor = ratio;
    }

    public int size (){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Enumeration<K> keys() {
        return null;
    }

    @Override
    public Enumeration<V> elements() {
        return null;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    public V put (K key, V value) {
        //code this method
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}


