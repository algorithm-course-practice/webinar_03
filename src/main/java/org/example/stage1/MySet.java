package org.example.stage1;

public interface MySet<E> {

    boolean add(E element);

    boolean contains(E element);

    boolean remove(E element);

    int size();

    Iterable<E> entrySet();
}
