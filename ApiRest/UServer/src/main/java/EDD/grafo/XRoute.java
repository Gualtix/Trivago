package EDD.grafo;

import EDD.DAO.DataStructure;
import EDD.list.List;
import filesManager.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class XRoute extends DataStructure implements Comparable {

    public int codigo;
    public String nombre;
    public String color;
    public double precio;

    public List<XStation> estaciones;

    public XRoute(){
        estaciones = new List<>();
    }

    public XRoute(int codigo,String nombre,String color,double precio){

        this.codigo = codigo;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;

        estaciones = new List<>();

    }



    @Override
    public int compareTo(Object o) {
        XRoute Tmp = (XRoute)o;

        return (Integer.compare(codigo,Tmp.codigo));
    }

    @Override
    public String nodeName() {
        return null;
    }

    @Override
    public String createNode() {
        return null;
    }

    @Override
    public String getJSON() {
        JSONObject root = new JSONObject();
        root.put("codigo", codigo);
        root.put("nombre", nombre);
        root.put("color", color);
        root.put("precio", precio);

        JSONArray array = new JSONArray();
        Iterator<XStation> iterator = estaciones.iterator();
        while (iterator.hasNext()) {
            XStation current = iterator.next();
            JSONObject object = new JSONObject();
            object.put("origen", current.origen);
            object.put("destino", current.destino);
            object.put("trafico", current.trafico);

            array.put(object);
        }
        root.put("estaciones", array);

        return root.toString();
    }

    @Override
    public String graph() {
        return null;
    }
}