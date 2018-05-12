package EDD.grafo;


import EDD.list.List;
import EDD.tad.TADNodo;

public class Nodo implements Comparable {

    private TADNodo data;

    private List<Arista> aristas;

    public Nodo() {
        aristas = new List<>();
    }

    public Nodo(TADNodo value) {
        data = value;
        aristas = new List<>();
    }

    public TADNodo getData() {
        return data;
    }

    public void addData(TADNodo data) {
        this.data = data;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void addArista(Arista arista) {
        aristas.push_back(arista);
    }

    @Override
    public int compareTo(Object o) {
        return data.compareTo(((Nodo) o).getData());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(((Nodo) obj).getData());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
