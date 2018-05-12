package EDD.hashTable;

import DAO.DataStructure;

public class HashTable<T extends DataStructure> {

    private int capacity;
    private int size;
    private double loadFactor;
    private NodeHash<T> nodes[];

    public HashTable() {
        capacity = 11;
        size = 0;
        loadFactor = 0.75;
        nodes = new NodeHash[capacity];
    }

    public HashTable(int initialCapacity) {
        capacity = initialCapacity;
        size = 0;
        loadFactor = 0.75;
        nodes = new NodeHash[this.capacity];
    }

    public HashTable(int initialCapacity, double loadFactor) {
        capacity = initialCapacity;
        size = 0;
        this.loadFactor = loadFactor;
        nodes = new NodeHash[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        capacity = 11;
        size = 0;
        nodes = null;

        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
    }

    public void put(T data) {
        int index = hashFunction(data);
        System.out.println("INDEX->" + index);

        if (nodes[index] != null) {
            System.out.println("COLISIÓN");
            int iteration = 1;
            while (true) {
                int newIndex = index + sFunction(data, iteration);

                while (true) {
                    int diference = newIndex - capacity;
                    if (diference < 0) {
                        index = newIndex;
                        break;
                    }
                    else
                        newIndex = diference;
                }

                System.out.println("INDEX->" + index);
                if (nodes[index] == null)
                    break;

                iteration++;
            }
        }

        nodes[index] = new NodeHash<>(data);
        size++;

        loadFactor = calculateLoadFactor();

        if (loadFactor >= 70) {
            System.out.println("Rehashing FE->" + loadFactor);
            nodes = rehashing();
        }
    }

    public T get(T data) {
        int index = hashFunction(data);

        if (nodes[index] == null)
            return null;

        if (nodes[index].compareTo(data) == 0)
            return nodes[index].getData();
        else
        {
            int iteration = 1;
            while (true) {
                int newIndex = index + sFunction(data, iteration);

                while (true) {
                    int diference = newIndex - capacity;
                    if (diference < 0) {
                        index = newIndex;
                        break;
                    }
                    else
                        newIndex = diference;
                }

                if (nodes[index] == null)
                    return null;

                if (nodes[index].compareTo(data) == 0)
                    return nodes[index].getData();

                iteration++;
            }
        }
    }

    public String graph() {
        String text = "HASH [label = \"";

        for (int i = 0; i < nodes.length; i++) {
            text += "<" + i + "> " + i;

            if (i < (nodes.length - 1))
                text += " | ";
        }

        text += "\"]\n";

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                continue;
            }

            text += nodes[i].createNode();
            text += "\n";
            text += "HASH : " + i;
            text += " -> ";
            text += nodes[i].nodeName();
            text += "\n";
        }

        return text;
    }

    private double calculateLoadFactor() {
        return (size * 100) / capacity;
    }

    private int hashFunction(T data) {
        return data.hashCode() % capacity;
    }

    private int sFunction(T data, int i) {
        return (data.hashCode() % 7 + 1) * i;
    }

    private NodeHash<T>[] rehashing() {
        int _m = 0;
        _m = nextPrimeNumber();
        size = 0;
        NodeHash<T> temporalNodes[] = new NodeHash[capacity];
        System.arraycopy(nodes, 0, temporalNodes, 0, capacity);

        clear();
        capacity = _m;
        nodes = new NodeHash[capacity];

        for (NodeHash<T> n : temporalNodes) {
            if (n != null) {
                T temporal = n.getData();
                put(temporal);
            }
        }

        return nodes;
    }

    private int nextPrimeNumber() {
        int x = capacity + 1;

        while (true) {
            int d = 0;
            for (int i = 2; i <= x / 2; i++) {
                if (x % i == 0)
                    d++;
            }
            if (d < 2)
                break;
            else
                x++;
        }

        return x;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "capacity=" + capacity +
                ", size=" + size +
                ", loadFactor=" + loadFactor +
                '}';
    }
}
