package EDD.tad;

import DAO.DataStructure;
import EDD.grafo.Arista;
import EDD.grafo.Nodo;
import EDD.grafo.Ruta;
import EDD.list.List;

public class TADHash extends DataStructure {

    private int codigo;
    private String nombre;
    private String color;
    private double precio;

    private Nodo origen;
    Ruta estaciones;

    public TADHash() {
    }

    public TADHash(int codigo, String nombre, String color, double precio, Nodo origen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
        this.origen = origen;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Nodo getOrigen() {
        return origen;
    }

    public void setOrigen(Nodo origen) {
        this.origen = origen;
    }

    public Ruta getEstaciones() {
        return estaciones;
    }

    public void addRuta(Arista arista) {
        this.estaciones.addArista(arista);
    }

    @Override
    public String toString() {
        return String.format("%d : %s\nQ. %.2f",
                            codigo, nombre, precio);
    }

    @Override
    public String nodeName() {
        return String.format("node%s", this.hashCode());
    }

    @Override
    public String createNode() {
        return String.format("%s [label = \"\"]",
                nodeName());
    }

    @Override
    public String getJSON() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        TADHash temp = (TADHash)o;
        return Integer.compare(codigo, temp.getCodigo());
    }
}
