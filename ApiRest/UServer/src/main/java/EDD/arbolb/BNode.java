package arbolb;

import DAO.DataStructure;

import java.util.Arrays;

public class BNode<T extends DataStructure> extends DataStructure {

    private int k;
    private int size;
    private T info[];
    private BNode<T> childs[];

    public BNode(int k) {
        this.k = k;
        size = 0;
        info = (T[]) new DataStructure[k + 1];
        childs = new BNode[k + 1];
    }

    public int getK() {
        return k;
    }

    public int size() {
        return size;
    }

    public T getInfo(int index) {
        return info[index];
    }

    public void setInfo(T value, int index) {
        info[index] = value;
    }

    public void pushInfo(T value) {
        info[size] = value;
        size++;
        Arrays.sort(info, 0, size);
    }

    public void pushInfo(T value, int index) {
        for (int x = size; x > index; x--)
            info[x] = info[x - 1];

        info[index] = value;
        size++;
    }

    public BNode<T> getChild(int index) {
        return childs[index];
    }

    public void setChilds(BNode<T> child, int index) {
        childs[index] = child;
    }

    public void pushChild(BNode<T> child) {
        childs[size - 1] = child;
    }

    public void pushChild(BNode<T> child, int index) {
        for (int x = size; x > index; x--)
            childs[x] = childs[x - 1];
        childs[index] = child;
    }

    public Object[] divide() {
        BNode<T> leftChild = new BNode<>(k);
        BNode<T> rightChild = new BNode<>(k);
        Object[] result = new Object[3];
        int med = k / 2;

        for (int i = 0; i < med; i++)
        {
            leftChild.pushInfo(info[i]);
            leftChild.pushChild(childs[i]);
        }
        for (int i = med + 1, a = 0; i <= k; i++, a++)
        {
            rightChild.pushInfo(info[i]);
            rightChild.pushChild(childs[i]);
        }

        result[0] = info[med];
        result[1] = leftChild;
        result[2] = rightChild;

        return result;
    }

    @Override
    public String toString() {
        String text = "";
        for (int i = 0; i < k; i++) {
            if (info[i] != null)
                text += String.format("<%d> | %s |",
                    i, info[i].toString());
            else
                text += String.format("<%d> |  |",
                        i);
        }
        text += String.format("<%d>", k);

        return text;
    }

    @Override
    public String nodeName() {
        return String.format("node%d", hashCode());
    }

    @Override
    public String createNode() {
        String node = String.format("%s [label = \"%s\"]\n",
                nodeName(), toString());

        return node;
    }

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
