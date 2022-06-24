package ru.vsu.cs.course1;

import java.util.Iterator;

public class SimpleLinkedList{

    public static class SimpleLinkedListException extends Exception {
        public SimpleLinkedListException(String message) {
            super(message);
        }
    }

    private static class SimpleLinkedListNode {
        public int value;
        public SimpleLinkedListNode next;

        public SimpleLinkedListNode(int value, SimpleLinkedListNode next) {
            this.value = value;
            this.next = next;
        }

        public SimpleLinkedListNode(int value) {
            this(value, null);
        }
    }

    private static SimpleLinkedListNode head = null;  // first, top
    private SimpleLinkedListNode tail = null;  // last
    private static int size = 0;

    // O(1)
    public void addFirst(int value) {
        head = new SimpleLinkedListNode(value, head);
        if (size == 0) {
            tail = head;
        }
        size++;
    }

    // O(1)
    public void addLast(int value) {
        if (size == 0) {
            head = tail = new SimpleLinkedListNode(value);
        } else {
            tail.next = new SimpleLinkedListNode(value);
            tail = tail.next;
        }
        size++;
    }

    private static void checkEmptyError() throws SimpleLinkedListException {
        if (size == 0) {
            throw new SimpleLinkedListException("Empty list");
        }
    }

    // O(n)
    private static SimpleLinkedListNode getNode(int index) {
        SimpleLinkedListNode curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    // O(1)
    public void removeFirst() throws SimpleLinkedListException {
        checkEmptyError();
        head = head.next;
        if (size == 1) {
            tail = null;
        }
        size--;
    }

    // O(n)
    public void removeLast() throws SimpleLinkedListException {
        checkEmptyError();
        if (size == 1) {
            head = tail = null;
        } else {
            tail = getNode(size - 2);
            tail.next = null;
        }
        size--;
    }

    // O(n)
    public void remove(int index) throws SimpleLinkedListException {
        checkEmptyError();
        if (index < 0 || index >= size) {
            throw new SimpleLinkedListException("Incorrect index");
        }
        if (index == 0) {
            removeFirst();
        } else {
            SimpleLinkedListNode prev = getNode(index - 1);
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
            size--;
        }
    }

    public static int[] insertAfterMax(int[] arr) throws SimpleLinkedListException {

        SimpleLinkedList list = new SimpleLinkedList();
        for (int j : arr) {
            list.addLast(j);
        }

        SimpleLinkedListNode maxNode = getNode(0);
        for (int i = 1; i < list.size(); i++) {
            if (get(i) >= maxNode.value) {
                maxNode = getNode(i);
            }
        }

        SimpleLinkedListNode afterMax = maxNode.next;
        SimpleLinkedListNode newNode = new SimpleLinkedListNode(-maxNode.value);
        maxNode.next = newNode;
        newNode.next = afterMax;
        size++;

        int[] res = new int[arr.length + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = get(i);
        }

        return res;
    }

    // O(1)
    public int size() {
        return size;
    }

    // O(n)
    public static int get(int index) throws SimpleLinkedListException {
        checkEmptyError();
        return getNode(index).value;
    }

    // O(1)
    public int getFirst() throws SimpleLinkedListException {
        checkEmptyError();
        return head.value;
    }

    // O(1)
    public int getLast() throws SimpleLinkedListException {
        checkEmptyError();
        return tail.value;
    }


}

