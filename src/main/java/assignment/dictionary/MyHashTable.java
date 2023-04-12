package assignment.dictionary;

/*

 */

//
import java.util.*;
import java.util.Dictionary;

 class Entry<K, V> {
    K key;
    V value;
    int hashCode;
    Entry<K, V> next;
    public Entry(K key, V value, int hashCode){
        this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}


public class MyHashTable<K,V>
//        extends Dictionary<K,V>
//     implements Map<K,V>, Cloneable, java.io.Serializable
{
    private double loadFactor;
    private int size;

    public MyHashTable() {
        this.loadFactor = 0.75; //the default load factor is 0.75
        this.size = 11;         //the default capacity of the hash table is 11
    }

    public MyHashTable(int initSize) {
        if (initSize < 0){
            throw new IllegalArgumentException();
        }
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
        return size() == 0;
    }

}


