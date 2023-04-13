package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.util.Dictionary;



public class MyHashTable<K,V>
//        extends Dictionary<K,V>
//     implements Map<K,V>, Cloneable, java.io.Serializable
{
    private float loadFactor;
    private int threshold;
    private int size;
    private int initSize = 150;
    private int initCapacity = 500;
    private AList<Entry<K, V>> arrList;

    public MyHashTable() {
        this(150, 0.75f);
    }

    public MyHashTable(int initSize, float ratio) {
        if ((initSize < 0) || (ratio <= 0.0f)){
            throw new IllegalArgumentException("size & loadFactor must be greater than 0");
        }
        this.loadFactor = ratio;
        this.threshold = (int) (initSize * loadFactor);
        size = 0;
        arrList = new AList<>();
        // create empty slots
        for (int i = 0; i < initSize; i++){
            arrList.add(null);
        }
    }

    public int hashFunc(K key){
        int hash = key.hashCode();
        return hash;
    }

    public int size (){
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private int getIndex(K key){
        int hash = hashFunc(key);
        int idx = hash % initSize;
        if (idx < 0)
            idx *= -1;
        return idx;
    }

    public void clear(){
        arrList.clear();
    }

    public V put(K key, V value){
        // throw error if value is null
        if (value == null){
            throw new NullPointerException("value is null");
        }
        int hash = hashFunc(key);
        int idx = getIndex(key); //index in table
        Entry<K, V> node = arrList.getEntry(idx);

        while(node != null){
            if (node.key.equals(key) && node.hash == hash){
                node.value = value;
                return value;
            }
            node = node.next;
        }
        System.out.println(hash + " " + idx);
        return null;
    }

    public V remove(K key){
        return null;
    }

    public V get(K key){
        int idx = getIndex(key);
        int hash = hashFunc(key);
        Entry<K, V> node = arrList.getEntry(idx);
        while (node != null){
            if (node.key.equals(key) && node.hash == hash)
                return node.value;
            node = node.next;
        }
        return null;
    }

    public boolean containsKey(Object key){
        return false;
    }

    public AList<K> keySet(){
        return null;
    }

    public AList<V> values(){
        return null;
    }

    // Entry class for all the hash nodes
    private static class Entry<K, V> {
        K key;
        V value;
        int hash;
        Entry<K, V> next;
        public Entry(K key, V value, int hash){
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

}


