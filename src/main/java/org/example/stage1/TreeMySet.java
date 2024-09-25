package org.example.stage1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *         5
 *    3       8
 * 1   4   6
 *            7
 *
 * @param <E>
 */

public class TreeMySet<E extends Comparable> implements MySet<E> {

    Node<E> root = new Node<>();

    int size;

    @Override
    public boolean add(E element) {
        Node<E> current = root;

        while (current.value != null) {
            if (current.value.equals(element)) {
                return false;
            }
            if (element.compareTo(current.value) > 0) {
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
        current.value = element;
        ++size;
        return true;
    }

    @Override
    public boolean contains(E element) {
        Node<E> current = root;

        while (current != null && current.value != null) {
            if (current.value.equals(element)) {
                return true;
            }
            if (element.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    /**
     * 5
     * \
     * 9
     * /
     * 6
     * \
     * 7
     *
     * @param element
     * @return
     */
    @Override
    public boolean remove(E element) {
        Node<E> current = root;
        while (current != null && !element.equals(current.value)) {
            if (element.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null) {
            return false;
        }
        // right
        E value = getMinWithRemove(current.right);
        if (value == null) {
            value = getMaxWithRemove(current.left);
        }


        current.value = value;
        --size;
        return true;
    }

    private E getMaxWithRemove(Node<E> left) {
        if (root == null || root.value == null) {
            return null;
        }
        Node<E> current = root;
        while (current != null && current.value != null && current.right != null) {
            current = current.right;
        }
        E result = current.value;
        if (current.left != null) {
            Node<E> tmp = current.left;

            current.left = tmp.left;
            current.right = tmp.right;
            current.value = tmp.value;
        } else {
            current.value = null;
        }
        return result;
    }

    private E getMinWithRemove(Node<E> root) {
        if (root == null || root.value == null) {
            return null;
        }
        Node<E> current = root;
        while (current != null && current.value != null && current.left != null) {
            current = current.left;
        }
        E result = current.value;
        if (current.right != null) {
            Node<E> tmp = current.right;

            current.left = tmp.left;
            current.right = tmp.right;
            current.value = tmp.value;
        } else {
            current.value = null;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<E> entrySet() {
        List<E> list = new ArrayList<>();
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<E> current = stack.pop();
            if (current.value == null) {
                continue;
            }
            if (list.contains(current.value)) {
                continue;
            }
            if (current.left != null && current.left.value != null) {
                if (!list.contains(current.left.value)) {
                    stack.push(current);
                    stack.push(current.left);
                    continue;
                }
                list.add(current.value);
            }

            if (current.right != null && current.right.value != null) {
                stack.push(current);
                stack.push(current.right);
                continue;
            }

            if (
                    (current.left == null || current.left.value == null)
                            && (current.right == null || current.right.value == null)
            ) {
                list.add(current.value);
            }

        }


        return list;
    }


    static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;
    }
}
