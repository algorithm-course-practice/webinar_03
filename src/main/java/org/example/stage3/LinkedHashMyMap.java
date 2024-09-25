package org.example.stage3;

import org.example.stage2.HashMyMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @see java.util.LinkedHashMap
 * @param <K>
 * @param <V>
 */
public class LinkedHashMyMap<K,V> extends HashMyMap<K,V> {

    List<Pair<K,V>> insertOrder = new ArrayList<>();

    @Override
    public boolean put(K key, V value) {
        HashMyMap.Pair<K, V> pair = findAndCreate(key, value);
        if(!insertOrder.contains(pair)){
            insertOrder.add(pair);
        }
        return true;
    }

    @Override
    public Iterable<K> keySet() {
        ArrayList<K> ks = new ArrayList<>();
        for (Pair<K, V> pair : insertOrder) {
            ks.add(pair.key);
        }
        return ks;
    }

    @Override
    public boolean remove(K key) {
        Pair<K, V> pair = removeByKey(key);
        if(pair!= null){
            insertOrder.remove(pair);
        }
        return pair != null;
    }
}
