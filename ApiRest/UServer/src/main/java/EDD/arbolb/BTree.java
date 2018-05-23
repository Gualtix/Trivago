package EDD.arbolb;

import EDD.DAO.DataStructure;

import java.io.*;

import filesManager.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

public class BTree<T extends DataStructure> {

    private int k;
    private BNode<T> root;

    private static String FILENAME = "bTree";

    public BTree(int k) {
        this.k = k;
        root = new BNode<>(k);
    }

    public void add(T data) {
        if (root.size() == 0)
            root.pushInfo(data);
        else {
            root = add(data, root);
            assert root != null;
            if (root.size() > k)
                root = split(null, root, 0);
        }
    }

    private BNode<T> add(T data, BNode<T> current) {
        int i = 0;
        BNode<T> child;

        if (current.getInfo(i).compareTo(data) < 0)
        {
            for (int x = current.size() - 1; x >= i; x--) {
                if (current.getInfo(x).compareTo(data) == 0)
                    return current;
                if (current.getInfo(x).compareTo(data) < 0) {
                    i = x + 1;
                    break;
                }
            }
        }

        if (current.getChild(i) != null) {
            child = add(data, current.getChild(i));
            assert child != null;
            if (child.size() > k)
                current = split(current, child, i);
        }
        else
            current.pushInfo(data);

        return current;
    }

    public T get(T data) {
        return get(data, root);
    }

    private T get(T data, BNode<T> current) {
        if (current == null) {
            return null;
        }

        T result = null;
        for (int i = 0; i < current.size(); i++) {
            if (current.getInfo(i).compareTo(data) == 0)
                return current.getInfo(i);
            if (current.getInfo(i).compareTo(data) < 0)
                continue;
            result = get(data, current.getChild(i));
            if (result != null)
                break;
        }
        
        if (result == null)
            result = get(data, current.getChild(current.size()));

        return result;
    }

    private BNode<T> split(BNode<T> current, BNode<T> child, int i) {
        Object[] o = child.divide();
        if (current != null)
        {
            current.pushInfo((T) o[0], i);
            current.setChilds((BNode<T>) o[1], i);
            current.pushChild((BNode<T>) o[2], i + 1);
        }
        else {
            BNode<T> node = new BNode<>(k);
            node.pushInfo((T) o[0]);
            node.setChilds((BNode<T>) o[1], 0);
            node.setChilds((BNode<T>) o[2], 1);
            current = node;
        }

        return current;
    }

    public void graph() {
        String text = String.format("digraph %s {\n", FILENAME);
        text += "rankdir = TB\n";
        text += "node [shape = record]";
        text += graph(this.root);
        text += "\n}";

        FileManager fileManager = new FileManager(FILENAME, text);
        fileManager.createFile(".dot");

        String cmd = String.format("dot -Tpng %s.dot -o %s.png",
                FILENAME, FILENAME);

        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String graph(BNode<T> current) {
        String text = "";

        if (current == null)
            return text;

        text += current.createNode() + "\n";

        for (int i = 0; i <= k; i++) {
            if (current.getChild(i) != null) {
                text += String.format("%s : %d -> %s\n",
                        current.nodeName(), i, current.getChild(i).nodeName());

                text += graph(current.getChild(i));
            }
        }

        return text;
    }

    public void toJSON() {
        JSONArray array = new JSONArray();
        array = toJSON(root, array);

        FileManager fileManager = new FileManager(FILENAME, array.toString());
        fileManager.createFile("json");
    }

    private JSONArray toJSON(BNode<T> current, JSONArray array) {
        if (current == null) {
            return array;
        }

        for (int i = 0; i < current.size(); i++) {
            array = toJSON(current.getChild(i), array);
            array.put(new JSONObject(current.getInfo(i).getJSON()));
        }
        array = toJSON(current.getChild(current.size()), array);

        return array;
    }

    public String toBase64() {
        FileManager fileManager = new FileManager(FILENAME);
        return fileManager.getImageBase64(".png");
    }
}
