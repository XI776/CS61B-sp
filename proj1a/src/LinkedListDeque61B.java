

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private Node head;
    private int _size;
    public LinkedListDeque61B() {
        head = new Node();
        _size = 0;
        head.next = head;
        head.prev = head;
    }

    @Override
    public void addFirst(T x) {
        Node node = new Node(x);
        if (head.next == head) {
            head.next = node;
            node.prev = head;
            node.next = head;
            head.prev = node;
        } else {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }
        _size++;
    }

    @Override
    public void addLast(T x) {
        Node node = new Node(x);
        if (head.next == head) {
            head.next = node;
            node.prev = head;
            node.next = head;
            head.prev = node;
        } else {
            head.prev.next = node;
            node.next = head;
            node.prev = head.prev;
            head.prev = node;
        }
        _size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node temp = this.head.next;
        while (temp != head) {
            list.add(temp.data);
            temp = temp.next;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return  _size == 0;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public T removeFirst() {
        if (_size == 0) {
            return null;
        }
        T result = this.head.next.data;
        this.head.next =  this.head.next.next;
        this.head.next.next.prev = this.head;
        return result;
    }

    @Override
    public T removeLast() {
        if (_size == 0) {
            return null;
        }
        T  result = this.head.prev.data;
        head.prev.prev.next = head;
        head.prev = head.prev.prev;

        return result;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= _size) {
            return null;
        }
        Node temp = this.head.next;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= _size) {
            return null;
        }
        return getRecursiveNode(head.next, index);
    }
    public T getRecursiveNode(Node node, int index) {
        if (index == 0) {
            return node.data;
        } else {
            return getRecursiveNode(node.next, index - 1);
        }
    }
    class Node {
        Node next;
        Node prev;
        T data;
        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
        Node() {
            this.data = null;
            this.next = null;
            this.prev = null;
        }


    }

}
