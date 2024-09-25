package org.example.stage3;

import org.example.stage2.HashMySet;

import java.util.ArrayList;
import java.util.List;

/**
 * @see java.util.LinkedHashSet
 * @param <E>
 */
public class LinkedHashMySet<E> extends HashMySet<E> {

    List<E> insertOrder = new ArrayList<>();

    @Override
    public boolean add(E element) {
        boolean res = super.add(element);
        if(res){
            insertOrder.add(element);
        }
        return res;
    }

    @Override
    public boolean remove(E element) {
        boolean res = super.remove(element);
        if(res){
            insertOrder.remove(element);
        }
        return res;
    }

    @Override
    public Iterable<E> entrySet() {
        return new ArrayList<>(insertOrder);
    }
}
