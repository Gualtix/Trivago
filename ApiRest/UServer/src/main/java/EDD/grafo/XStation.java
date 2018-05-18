package EDD.grafo;

import DAO.DataStructure;

public class XStation extends DataStructure implements Comparable {

    public int origen;
    public int destino;
    public double trafico;

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
        return null;
    }
}
