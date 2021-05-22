package ru.vsu.cs.ivanov;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    private class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head = null;
    private Node<T> tail = null;
    private int count = 0;

    public void addHead(T data) {
        head = new Node<>(data, head);
        if (count == 0) {
            tail = head;
        }
        count++;
    }

    public void addTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (count > 0) {
            tail.next = newNode;
        } else {
            head = newNode;
        }
        tail = newNode;
        count++;
    }

    public T getHead() throws Exception {
        emptyError();
        return head.data;
    }

    public T getTail() throws Exception {
        emptyError();
        return tail.data;
    }

    private void emptyError() throws Exception {
        if (count == 0) {
            throw new Exception("List is empty");
        }
    }

    public T get(int index) throws Exception {
        return getNode(index).data;
    }

    private Node<T> getNode(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }
        Node<T> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T removeHead() throws Exception {
        T data = getHead();
        head = head.next;
        count--;
        if (count == 0) {
            tail = null;
        }
        return data;
    }

    public T removeTail() throws Exception {
        T data = getTail();
        count--;
        if (count == 0) {
            head = tail = null;
        } else {
            tail = getNode(count - 1);
            tail.next = null;
        }
        return data;
    }

    public T remove(int index) throws Exception {
        if (index < 0 || index >= count) {
            throw new Exception("Wrong index");
        }

        T data;
        if (index == 0) {
            data = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node<T> prev = getNode(index - 1);
            data = prev.next.data;
            prev.next = prev.next.next;
            if (prev.next == null) {
                tail = prev;
            }
        }
        count--;
        return data;
    }

    public void insert(int index, T data) throws Exception {
        if (index < 0 || index > count) {
            throw new Exception("Wrong index");
        }
        if (index == 0) {
            addHead(data);
        } else {
            Node prev = getNode(index - 1);
            prev.next = new Node(data, prev.next);
            count++;
        }
    }

    public void clear() {
        head = tail = null;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        class MyLinkedListIterator implements Iterator<T> {
            Node<T> curr;

            public MyLinkedListIterator(Node<T> head) {
                curr = head;
            }

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T result = curr.data;
                curr = curr.next;
                return result;
            }
        }

        return new MyLinkedListIterator(head);
    }

    public void reverse() {
        head = reverseList(head);
    }

    private Node<T> reverseList(Node<T> curHead) {
        if(curHead == null) {
            return null;
        }

        if(curHead.next == null) {
            return curHead;
        }

        Node<T> newHeadNode = reverseList(curHead.next);
        curHead.next.next = curHead;
        curHead.next = null;
        return newHeadNode;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (T item : this) {
            str.append(item);
            str.append(" ");
        }
        return str.toString();
    }
}
