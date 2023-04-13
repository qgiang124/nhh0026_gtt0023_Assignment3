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
    private AList<Entry<K, V>> arrList = new AList<>(500);

    public MyHashTable() {
        this(500, 0.75f);
    }

    public MyHashTable(int initSize, float ratio) {
        if ((initSize < 0) || (ratio <= 0.0f)){
            throw new IllegalArgumentException("size & loadFactor must be greater than 0");
        }
        this.loadFactor = ratio;
        this.threshold = (int) (initSize * loadFactor);

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

    public void clear(){
        arrList.clear();
    }

    public V put(K key, V value){
        // throw error if value is null
        if (value == null){
            throw new NullPointerException("value is null");
        }
        int hash = hashFunc(key);
        int idx = (hash & 0x7FFFFFFF) % arrList.getLength(); //index in table
        Entry<K, V> node = new Entry<K, V>(key, value, hash);

        // if key already existed, update with new value
        Iterator<Entry<K, V>> itr = arrList.iterator();
        while (itr.hasNext()){
            if (itr.equals(node)){
                V old = node.value;
                node.value = value;
                return old;
            }
        }

        // add a new entry to arrList
        arrList.add(idx, node);
        size++;
        return null;
    }

    public V remove(K key){
        return null;
    }

    public V get(K key){
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
        public Entry(K key, V value, int hash){
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }

}


