package EDD.hashTable;

import EDD.DAO.DataStructure;
import filesManager.FileManager;

import java.io.IOException;

public class HashTable<T extends DataStructure> {

    private static final String FILENAME_MAP = "Mapa";
    private int capacity;
    private int size;
    private double loadFactor;
    private NodeHash<T> nodes[];

    private static String FILENAME = "ListaRutas";

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

    public void graph() {

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

        String dot = String.format("digraph %s {\n" +
                        "rankdir = LR\n" +
                        "nodesep = .05\n" +
                        "node [shape = record, width = 1.5, height = .5]\n" +
                        "%s\n}",
                FILENAME, text);

        FileManager fileManager = new FileManager(FILENAME, text);
        fileManager.createFile("dot");

        String cmd = String.format("dot -Tpng %s.dot -o %s.png",
                FILENAME, FILENAME);

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toBase64() {
        FileManager fileManager = new FileManager(FILENAME);
        return fileManager.getImageBase64(".png");
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

    /* GRAPH MAP */
    public String graphMap() {
        String text = String.format("digraph %s {\n" +
                "graph [layout = neato, rankdir = LR]\n" +
                "graph [bgcolor = white, fontname = Arial, fontcolor = blue]\n" +
                "node  [shape = circle, fontname = MathJax_SansSerif, fontsize = 22, margin = 0, style = filled, fillcolor = \"#ccff66\"]\n" +
                "node  [fixedsize = true,width = 0.5, labelloc = top, margin = 1.5]\n" +
                "edge  [penwidth = 5, fontname = MathJax_SansSerif, fontcolor = red, fontsize = 22]\n", FILENAME_MAP);

        for (int i = 0; i < capacity; i++) {
            NodeHash<T> currentNode = nodes[i];

            if (currentNode != null)
                text += currentNode.graphMap();
        }

        return text + "}";
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

    public T pop(T oldData) {
        int index = hashFunction(oldData);

        if (nodes[index] == null)
            return null;

        if (nodes[index].compareTo(oldData) == 0) {
            T temp = nodes[index].getData();
            nodes[index] = null;
            size--;
            return temp;
        }
        else
        {
            int iteration = 1;
            while (true) {
                int newIndex = index + sFunction(oldData, iteration);

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

                if (nodes[index].compareTo(oldData) == 0) {
                    T temp = nodes[index].getData();
                    nodes[index] = null;
                    size--;
                    return temp;
                }

                iteration++;
            }
        }
    }
}
