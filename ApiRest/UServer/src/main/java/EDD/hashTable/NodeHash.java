package hashTable;

import daoStructure.DataStructure;

public class NodeHash<T extends DataStructure> implements Comparable {

    private T data;
    private int state;

    public NodeHash() {
    }

    public NodeHash(T data) {
        this.data = data;
        state = 1;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        this.state = 1;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String nodeName()
    {
        return data.nodeName();
    }

    public String createNode()
    {
        return data.createNode();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public int compareTo(Object o) {
        return data.compareTo(o);
    }
}
