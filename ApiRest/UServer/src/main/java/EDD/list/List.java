package EDD.list;

import java.util.Iterator;

public class List<T extends Comparable> {

    private NodeList<T> head;
    private NodeList<T> tail;

    public List() {
    }

    public List(List<T> list) throws CloneNotSupportedException {
        if (list.first() == list.last())
            head = tail = (NodeList<T>) list.first().clone();
        else {
            head = (NodeList<T>) list.first().clone();
            tail = (NodeList<T>) list.last().clone();
        }
    }

    public NodeList<T> first() {
        return head;
    }

    public NodeList<T> last() {
        return tail;
    }

    public T front() {
        return isEmpty() ? null : head.getData();
    }

    public T back() {
        return isEmpty() ? null : tail.getData();
    }

    public T push_front(T data) {
        if (isEmpty())
            head = tail = new NodeList<>(data);
        else {
            NodeList<T> node = new NodeList<>(data);
            node.setNext(head);
            head.setPreview(node);
            head = node;
        }

        return head.getData();
    }

    public T push_back(T data) {
        if (isEmpty())
            head = tail = new NodeList<>(data);
        else {
            NodeList<T> node = new NodeList<>(data);
            tail.setNext(node);
            node.setPreview(tail);
            tail = node;
        }
        return tail.getData();
    }

    public void prepend(List<T> list) throws CloneNotSupportedException {
        if (isEmpty()) {
            set(list);
            return;
        }

        NodeList<T> node = (NodeList<T>) list.last().clone();
        node.setNext(head);
        head.setPreview(node);

        if (list.first() == list.last())
            head = node;
        else
            head = (NodeList<T>) list.first().clone();
    }

    public void append(List<T> list) throws CloneNotSupportedException {
        if (isEmpty()) {
            set(list);
            return;
        }

        NodeList<T> node = (NodeList<T>) list.first().clone();
        tail.setNext(node);
        node.setPreview(tail);
        if (list.first() == list.last())
            tail = node;
        else
            tail = (NodeList<T>) list.last().clone();
    }

    private void set(List<T> list) throws CloneNotSupportedException {
        if (list.first() == list.last())
            head = tail = (NodeList<T>) list.first().clone();
        else {
            head = (NodeList<T>) list.head.clone();
            tail = (NodeList<T>) list.tail.clone();
        }
    }

    public void pop_back() {
        if (!isEmpty()) {
            tail = tail.getPreview();
            if (tail != null)
                tail.setNext(null);
        }
    }

    public void pop_front() {
        if (!isEmpty()) {
            head = head.getNext();
            if (head != null)
                head.setPreview(null);
        }
    }

    public boolean edit(T data) {
        if (isEmpty())
            return false;

        NodeList<T> nodo = head;

        while (nodo != null) {
            if (nodo.equals(data))
                break;
            nodo = nodo.getNext();
        }

        if (nodo != null) {
            nodo.setData(data);
            return true;
        }
        else
            return false;
    }

    public boolean isEmpty() {
        return ( head == null && tail == null );
    }

    public T get(T data) {
        if (isEmpty())
            return null;

        NodeList<T> node = head;

        while (node != null) {
            if (node.equals(data))
                break;
            node = node.getNext();
        }

        return (node != null) ? node.getData() : null;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public MyIterator iterator() {
        return new MyIterator(head);
    }

    private class MyIterator implements Iterator<T> {

        private NodeList<T> current;

        public MyIterator(NodeList<T> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            NodeList<T> response = current;
            current = current.getNext();

            return response.getData();
        }

        @Override
        public void remove() {

        }
    }
}
