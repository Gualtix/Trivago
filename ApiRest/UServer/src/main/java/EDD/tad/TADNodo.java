package EDD.tad;


import EDD.DAO.DataStructure;

public class TADNodo extends DataStructure implements Comparable {

    private static final int SCALE = 500;
    private int codigo;
    private String nombre;
    private double latitud;
    private double longitud;

    public TADNodo() {
    }

    public TADNodo(int codigo) {
        this.codigo = codigo;
        this.nombre = null;
        this.latitud = 0;
        this.longitud = 0;
    }

    public TADNodo(int codigo, String nombre, double latitud, double longitud) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public int compareTo(Object o) {
        TADNodo nodo = (TADNodo) o;
        return Integer.compare(codigo, nodo.getCodigo());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        TADNodo nodo = (TADNodo) obj;
        return codigo == nodo.getCodigo();
    }

    @Override
    public String toString() {
        return String.format("Codigo: %d\nNombre: %s\nLatitud: %4.3f\nLongitud: %4.3f",
                codigo, nombre, latitud, longitud);
    }

    @Override
    public String nodeName() {
        return String.format("%s", nombre);
    }

    @Override
    public String createNode() {
        double x, y;
        x = (latitud - 14) * SCALE;
        y = (longitud + 90) * -SCALE;
        return nombre + " [label = \"" + nombre + "\", pos = \"" + x + ", " + y + "!\"]";
    }

    @Override
    public String getJSON() {
        return null;
    }

    @Override
    public String graph() {
        return null;
    }
}
