package org.example.stage2;

import org.example.stage1.MyMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @see java.util.HashMap
 * @param <K>
 * @param <V>
 */
public class HashMyMap<K, V> implements MyMap<K, V> {

    List<Pair<K, V>>[] buckets;
    int size;

    public HashMyMap() {
        this.buckets = new List[3];
        for (int i = 0; i < 3; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean put(K key, V value) {
        findAndCreate(key, value);
        return true;
    }

    protected Pair<K, V> findAndCreate(K key, V value) {
        int index = key.hashCode() % buckets.length;
        List<Pair<K, V>> bucket = buckets[index];
        Pair<K, V> pair = searchPair(key, bucket);
        if (pair != null) {
            pair.value = value;
            return pair;
        }
        pair = new Pair<>();
        pair.key = key;
        pair.value = value;
        bucket.add(pair);
        ++size;
        return pair;
    }

    private Pair<K, V> searchPair(K key, List<Pair<K, V>> bucket) {
        for (Pair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode() % buckets.length;
        List<Pair<K, V>> bucket = buckets[index];
        Pair<K, V> pair = searchPair(key, bucket);
        return pair != null;
    }

    @Override
    public boolean containsValue(V value) {
        for (List<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                if (pair.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Iterable<K> keySet() {
        List<K> keys = new ArrayList<>();
        for (List<Pair<K, V>> bucket : buckets) {
            for (Pair<K, V> pair : bucket) {
                keys.add(pair.key);
            }
        }
        return keys;
    }

    @Override
    public boolean remove(K key) {
        Pair<K, V> pair = removeByKey(key);
        return pair != null;
    }

    protected Pair<K, V> removeByKey(K key) {
        int index = key.hashCode() % buckets.length;
        List<Pair<K, V>> bucket = buckets[index];
        Pair<K, V> pair = searchPair(key, bucket);
        if (pair != null) {
            bucket.remove(pair);
            size--;
            return pair;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public static class Pair<K, V> {
        public K key;
        public V value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}
