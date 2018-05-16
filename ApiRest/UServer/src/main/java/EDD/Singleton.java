package EDD;

import EDD.arbolb.BTree;
import EDD.grafo.Grafo;
import EDD.grafo.XRoute;
import EDD.hashTable.HashTable;
import EDD.list.List;
import EDD.tad.TADArbolB;
import EDD.tad.TADHash;
import EDD.tad.TADNodo;

public class Singleton {

    private int idTicket;
    private BTree<TADArbolB> arbol;
    private Grafo grafo;
    private HashTable<TADHash> hash;

    private List<TADNodo> StationList;
    private List<XRoute>  XRouteList;

    private static Singleton instance = null;

    public static synchronized Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        
        return instance;
    }

    private Singleton() {
        idTicket = 200;
        arbol = new BTree<>(3);
        grafo = new Grafo();
        hash = new HashTable<>();
        StationList = new List<>();
        XRouteList = new List<>();
    }

    public void addStation(TADNodo St){
        StationList.push_back(St);
    }

    public List<TADNodo> getStationList(){
        return StationList;
    }

    public void addXRoute(XRoute XRt){
        XRouteList.push_back(XRt);
    }

    public List<XRoute> getXRouteList(){
        return XRouteList;
    }

    /* CONTADOR DE TICKETS */
    public int getIdTicket() {
        return idTicket;
    }



    public void incrementTicket() {
        idTicket++;
    }

    /* MANEJO DE ARBOL */
    public BTree<TADArbolB> getArbol() {
        return arbol;
    }

    /* MANEJO DE GRAFO */
    public Grafo getGrafo() {
        return grafo;
    }

    /* MANEJO DE TABLA HASH */
    public HashTable<TADHash> getHashTable() {
        return hash;
    }
}