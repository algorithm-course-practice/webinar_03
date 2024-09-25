package org.example.stage2;

import org.example.stage1.MySet;

import java.util.LinkedList;
import java.util.List;

/**
 *  [e1 e2 e3] [] []
 *
 *
 * @param <E>
 * @see java.util.HashMap
 */
public class HashMySet<E> implements MySet<E> {

    List<E>[] buckets;
    int size;

    public HashMySet() {
        this.buckets =  new List[3];
        for (int i = 0; i < 3; i++) {
            this.buckets[i] = new LinkedList<>();
        }
    }

    @Override
    public boolean add(E element) {
        int index = element.hashCode() % buckets.length;
        List<E> bucket = buckets[index];
        if(bucket.contains(element)){
            return false;
        }
        bucket.add(element);
        ++size;
        return true;
    }

    @Override
    public boolean contains(E element) {
        int index = element.hashCode() % buckets.length;
        List<E> bucket = buckets[index];
        return bucket.contains(element);
    }

    @Override
    public boolean remove(E element) {
        int index = element.hashCode() % buckets.length;
        List<E> bucket = buckets[index];
        if(!bucket.contains(element)){
            return false;
        }
        bucket.remove(element);
        --size;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<E> entrySet() {
        List<E> list = new LinkedList<>();
        for (List<E> bucket : buckets) {
            list.addAll(bucket);
        }
        return list;
    }
}
