package EDD.grafo;

import EDD.DAO.DataStructure;
import EDD.list.List;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

public class Ruta extends DataStructure implements Comparable, Cloneable {

    private double costo;
    private List<Arista> aristaList;

    public Ruta() {
        this.costo = 0.0;
        this.aristaList = new List<>();
    }

    public Ruta(Arista value) {
        this.costo = value.getData().getDistancia();
        this.aristaList = new List<>();
        addArista(value);
    }

    public Ruta(Ruta rutaActual) {
        this.costo = rutaActual.getCosto();
        addArtistas(rutaActual.getAristaList());
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<Arista> getAristaList() {
        return aristaList;
    }

    public void addArista(Arista arista) {
        costo += arista.getData().getDistancia() * arista.getData().getTrafico();
        this.aristaList.push_back(arista);
    }

    public void addArtistas(List<Arista> artistList) {
        try {
            this.aristaList.append(artistList);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void removeArtista() {
        aristaList.pop_back();
    }

    @Override
    public String toString() {
        return String.valueOf(costo);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    protected Object clone() {
        Ruta cloneRoute = new Ruta();
        cloneRoute.setCosto(costo);

        Iterator<Arista> iterator = aristaList.iterator();
        while (iterator.hasNext()) {
            Arista arista = iterator.next();
            cloneRoute.addArista(arista);
        }

        return cloneRoute;
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
        JSONArray jsa = new JSONArray();

        Iterator<Arista> iterator = aristaList.iterator();
        while (iterator.hasNext()) {
            Arista currentArista = iterator.next();

            JSONObject jso = new JSONObject();
            jso.put("origen_latitud", currentArista.getOrigen().getData().getLatitud());
            jso.put("origen_longitud", currentArista.getOrigen().getData().getLongitud());
            jso.put("destino_latitud", currentArista.getDestino().getData().getLatitud());
            jso.put("destino_longitud", currentArista.getDestino().getData().getLongitud());

            jsa.put(jso);
        }

        return jsa.toString();
    }

    @Override
    public String graph() {
        String text = "";
        List<Arista> aristaList = this.aristaList;

        Iterator<Arista> iterator = aristaList.iterator();
        while (iterator.hasNext()) {
            Arista currentArista = iterator.next();
            text += String.format("%s\n", currentArista.getOrigen().getData().createNode());
            text += String.format("%s\n", currentArista.getDestino().getData().createNode());
            text += String.format("%s -> %s [color = \"#c61328\", style=\"dashed\"]\n",
                    currentArista.getOrigen().getData().nodeName(), currentArista.getDestino().getData().nodeName());
        }

        return text;
    }
}
