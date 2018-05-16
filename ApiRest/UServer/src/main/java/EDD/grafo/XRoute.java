package EDD.grafo;

import EDD.list.List;
import EDD.tad.TADNodo;

import java.util.ArrayList;

public class XRoute implements Comparable {

    public int codigo;
    public String nombre;
    public String color;
    public double precio;

    public List<XStation> estaciones;


    public XRoute(){
        estaciones = new List<>();
    }

    @Override
    public int compareTo(Object o) {
        XRoute Tmp = (XRoute)o;

        return (Integer.compare(codigo,Tmp.codigo));
    }
}
