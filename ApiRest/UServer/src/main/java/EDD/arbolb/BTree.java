package EDD.arbolb;

import DAO.DataStructure;

public class BTree<T extends DataStructure> {

    private int k;
    private BNode<T> root;

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

    public String graph() {
        return String.format("\n%s\n", graph(root));
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
}
