package org.example.stage1;

public interface MyMap<K,V> {

    boolean put(K key, V value);

    boolean containsKey(K key);

    boolean containsValue(V value);

    boolean remove(K key);

    int size();

}
