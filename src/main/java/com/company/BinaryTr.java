package com.company;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class BinaryTr<T> {

    @Getter
    Node<T> main;
    Comparator<T> comparator;

    public BinaryTr(Node<T> main) {
        this.main = main;
    }

    public BinaryTr(Node<T> main, Comparator<T> comparator) {
        this.main = main;
        this.comparator = comparator;
    }

    public BinaryTr(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return getSize(main);
    }

    public void traverse(Node<T> node) {
        if (Objects.nonNull(node)) {

            traverse(node.left);
            System.out.println(node.value.toString());
            traverse(node.right);

        }
    }

    public Node<T> add(T value) {
        return main = add(main, value);
    }

    public Node<T> remove(T value) {
        return main = delete(main, value);
    }

    public void traverseLeverOrder() {
        if (main == null) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(main);

        while(!queue.isEmpty()) {
            Node<T> remove = queue.remove();

            System.out.println(remove.value.toString());

            if(Objects.nonNull(remove.left)) {
                queue.add(remove.left);
            }
            if(Objects.nonNull(remove.right)) {
                queue.add(remove.right);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private Node<T> add(Node<T> node, T value) {
        if (Objects.isNull(node)) {
            return new Node<>(value);
        }
        if (Objects.nonNull(comparator)) {
            int compare = comparator.compare(value, node.value);
            checkBranchForAddValue(node, value, compare);
        } else {
            Comparable<T> comparable = (Comparable<T>) value;
            int i = comparable.compareTo(node.value);
            checkBranchForAddValue(node, value, i);
        }

        return node;
    }

    private void checkBranchForAddValue(Node<T> node, T value, int compare) {
        if (compare < 0) {
            node.left = add(node.left, value);
        } else if (compare > 0) {
            node.right = add(node.right, value);
        }
    }

    private int getSize(Node<T> node) {
        if (Objects.isNull(node)) {
            return 0;
        } else {
            return 1 + getSize(node.left) + getSize(node.right);
        }
    }

    @SuppressWarnings("unchecked")
    private Node<T> delete(Node<T> node, T value) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (value.equals(node.value)) {
            if (Objects.isNull(node.left) && Objects.isNull(node.right)) {
                return null;
            }

            if (Objects.isNull(node.left)) {
                return node.right;
            }

            if (Objects.isNull(node.right)) {
                return node.left;
            }

            T smallestValue = findSmallestValue(node);
            node.value = smallestValue;
            node.right = delete(node.right, smallestValue);
        }

        if (Objects.nonNull(comparator)) {
            int compare = comparator.compare(value, node.value);
            if (compare < 0) {
                node.left = delete(node.left, value);
                return node;
            }
        } else {
            Comparable<T> comparable = (Comparable<T>) value;
            int i = comparable.compareTo(node.value);
            if (i < 0) {
                node.left = delete(node.left, value);
                return node;
            }
        }

        node.right = delete(node.right, value);
        return node;
    }

    private T findSmallestValue(Node<T> node) {
        if (Objects.isNull(node.left)) {
            return node.value;
        } else {
            return findSmallestValue(node.right);
        }
    }


    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }
}
