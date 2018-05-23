package EDD.tad;

import EDD.DAO.DataStructure;
import EDD.grafo.Arista;
import EDD.grafo.Nodo;
import EDD.grafo.Ruta;
import EDD.list.List;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class TADHash extends DataStructure {

    private int codigo;
    private String nombre;
    private String color;
    private double precio;

    private Nodo origen;
    Ruta estaciones;

    public TADHash() {
        estaciones = new Ruta();
    }

    public TADHash(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public TADHash(int codigo, String nombre, String color, double precio, Nodo origen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
        this.origen = origen;
        estaciones = new Ruta();
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
        return String.format("%d : %s\\nQ. %.2f",
                            codigo, nombre, precio);
    }

    @Override
    public String nodeName() {
        return String.format("node%s", this.codigo);
    }

    @Override
    public String createNode() {
        return String.format("%s [label = \"%s\"]",
                nodeName(), toString());
    }

    @Override
    public String getJSON() {
        JSONObject object = new JSONObject();
        object.put("codigo", codigo);
        object.put("nombre", nombre);
        object.put("color", color);
        object.put("precio", precio);

        JSONArray array = new JSONArray();
        return "";
    }

    @Override
    public String graph() {
        String text = "";
        List<Arista> aristaList = estaciones.getAristaList();

        Iterator<Arista> iterator = aristaList.iterator();
        while (iterator.hasNext()) {
            Arista currentArista = iterator.next();
            text += String.format("%s\n", currentArista.getOrigen().getData().createNode());
            text += String.format("%s\n", currentArista.getDestino().getData().createNode());
            text += String.format("%s -> %s [color = \"%s\", label = \"%.2f\"]\n",
                    currentArista.getOrigen().getData().nodeName(), currentArista.getDestino().getData().nodeName(), color, currentArista.getData().getPeso());
        }

        return text;
    }

    @Override
    public int compareTo(Object o) {
        TADHash temp = (TADHash)o;
        return Integer.compare(codigo, temp.getCodigo());
    }

    @Override
    public boolean equals(Object obj) {
        TADHash temp = (TADHash)obj;
        return nombre.equals(temp.getNombre());
    }

    @Override
    public int hashCode() {
        char[] chars = nombre.toCharArray();
        int hash = 0;
        for (int i = 0; i < chars.length; i++) {
            hash += chars[i];
        }

        return hash;
    }
}
