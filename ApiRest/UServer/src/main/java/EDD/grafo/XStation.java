package EDD.grafo;

import EDD.DAO.DataStructure;
import org.json.JSONObject;

public class XStation extends DataStructure implements Comparable {

    public int origen;
    public int destino;
    public double trafico;

    public XStation(int origen,int destino,double trafico){
        this.origen = origen;
        this.destino = destino;
        this.trafico = trafico;
    }

    public XStation(){

    }

    @Override
    public int compareTo(Object o) {
        return 0;
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
        JSONObject object = new JSONObject();
        object.put("origen", origen);
        object.put("destino", destino);
        object.put("trafico", trafico);

        return object.toString();
    }

    @Override
    public String graph() {
        return null;
    }
}
