package list;

public class NodeList<T extends Comparable> implements
        Comparable, Cloneable {

    private T data;
    private NodeList<T> preview;
    private NodeList<T> next;

    public NodeList() {
    }

    public NodeList(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeList<T> getPreview() {
        return preview;
    }

    public void setPreview(NodeList<T> preview) {
        this.preview = preview;
    }

    public NodeList<T> getNext() {
        return next;
    }

    public void setNext(NodeList<T> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(obj);
    }

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Object o) {
        T value = (T) o;
        return data.compareTo(o);
    }
}
