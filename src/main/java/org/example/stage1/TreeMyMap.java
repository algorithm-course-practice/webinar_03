package org.example.stage1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @see java.util.TreeMap
 * @param <K>
 * @param <V>
 */

public class TreeMyMap<K extends Comparable,V> implements MyMap<K,V>{

    Node<K,V> root = new Node<>();
    int size;

    @Override
    public boolean put(K key, V value) {
        Node<K, V> current = root;

        while (current.key != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return true;
            }
            if (key.compareTo(current.key) > 0) {
                if (current.right == null) {
                    current.right = new Node<>();
                }
                current = current.right;
            } else {
                if (current.left == null) {
                    current.left = new Node<>();
                }
                current = current.left;
            }
        }
        current.key = key;
        current.value = value;
        ++size;
        return true;
    }

    @Override
    public boolean containsKey(K key) {
       Node<K,V> current = root;

        while (current != null && current.key != null) {
            if (current.key.equals(key)) {
                return true;
            }
            if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(V value) {
        List<K> list = new ArrayList<>();
        Stack<Node<K,V>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<K,V> current = stack.pop();
            if (current.key == null) {
                continue;
            }
            if (list.contains(current.key)) {
                continue;
            }
            if(value.equals(current.value)){
                return true;
            }
            if (current.left != null && current.left.key != null) {
                if (!list.contains(current.left.key)) {
                    stack.push(current);
                    stack.push(current.left);
                    continue;
                }
                list.add(current.key);
            }

            if (current.right != null && current.right.key != null) {
                stack.push(current);
                stack.push(current.right);
                continue;
            }

            if (
                    (current.left == null || current.left.key == null)
                            && (current.right == null || current.right.key == null)
            ) {
                list.add(current.key);
            }

        }

        return false;
    }

    @Override
    public boolean remove(K key) {
        Node<K,V> current = root;
        while (current != null && current.key != null && !key.equals(current.key)) {
            if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null || current.key == null) {
            return false;
        }
        // right
        Node<K,V> replaceNode = getMinWithRemove(current.right);
        if (replaceNode == null) {
            replaceNode = getMaxWithRemove(current.left);
        }

        if(replaceNode != null){
            current.value = replaceNode.value;
            current.key = replaceNode.key;
        }else{
            current.key = null;
            current.value = null;
        }
        --size;
        return true;
    }

    private Node<K,V> getMaxWithRemove(Node<K,V> left) {
        if (root == null || root.key == null) {
            return null;
        }
        Node<K,V> current = root;
        while (current != null && current.key != null && current.right != null) {
            current = current.right;
        }
        Node<K,V> result = new Node<>();
        result.key = current.key;
        result.value = current.value;
        if (current.left != null) {
            Node<K,V> tmp = current.left;
            current.left = tmp.left;
            current.right = tmp.right;
            current.value = tmp.value;
            current.key = tmp.key;
        } else {
            current.value = null;
            current.key = null;
        }
        return result;
    }

    private Node<K,V> getMinWithRemove(Node<K,V> root) {
        if (root == null || root.value == null) {
            return null;
        }
        Node<K,V> current = root;
        while (current != null && current.value != null && current.left != null) {
            current = current.left;
        }
        Node<K,V> result = new Node<>();
        result.key = current.key;
        result.value = current.value;
        if (current.right != null) {
            Node<K,V> tmp = current.right;

            current.left = tmp.left;
            current.right = tmp.right;
            current.value = tmp.value;
            current.key = tmp.key;
        } else {
            current.value = null;
            current.key = null;
        }
        return result;
    }


    @Override
    public int size() {
        return size;
    }


    static class Node<E,V> {
        E key;
        Node<E,V> left;
        Node<E,V> right;
        V value;
    }
}
