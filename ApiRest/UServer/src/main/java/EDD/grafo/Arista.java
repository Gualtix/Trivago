package grafo;

import tad.TADArista;

public class Arista implements Comparable {

    private TADArista data;
    private Nodo destino;

    public Arista() {
    }

    public Arista(TADArista valueArista, Nodo destino) {
        this.data = valueArista;
        this.destino = destino;
    }

    public Arista(TADArista value) {
        data = value;
    }

    public Arista(Nodo value) {
        destino = value;
    }

    public TADArista getData() {
        return data;
    }

    public void setData(TADArista data) {
        this.data = data;
    }

    public Nodo setDestino(Nodo value) {
        destino = value;

        return destino;
    }

    public Nodo getDestino() {
        return destino;
    }

    @Override
    public int compareTo(Object o) {
        return destino.compareTo(((Arista) o).getDestino());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return destino.equals(((Arista) obj).getDestino());
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
