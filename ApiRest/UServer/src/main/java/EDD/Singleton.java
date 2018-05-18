package EDD;

import EDD.arbolb.BTree;
import EDD.grafo.Arista;
import EDD.grafo.Grafo;
import EDD.grafo.XRoute;
import EDD.grafo.XStation;
import EDD.hashTable.HashTable;
import EDD.list.List;
import EDD.tad.TADArbolB;
import EDD.tad.TADArista;
import EDD.tad.TADHash;
import EDD.tad.TADNodo;

import java.util.Iterator;

public class Singleton {

    private int idTicket;
    private BTree<TADArbolB> arbol;
    private Grafo grafo;
    private HashTable<XRoute> hash;

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

    private void fillGrafo() {

    }

    /* MANEJO DE TABLA HASH */
    public HashTable<XRoute> getHashTable() {
        return hash;
    }

    public void fillHashTable() {
        Iterator<XRoute> iterator = XRouteList.iterator();
        while (iterator.hasNext()) {
            XRoute route = iterator.next();

            TADHash tadHash = new TADHash();
            tadHash.setCodigo(route.codigo);
            tadHash.setColor(route.color);
            tadHash.setNombre(route.nombre);
            tadHash.setPrecio(route.precio);

            Iterator<XStation> iterator1 = route.estaciones.iterator();
            while (iterator1.hasNext()) {
                XStation station = iterator1.next();
                TADNodo tadorigen = new TADNodo(station.origen);
                TADNodo taddestino = new TADNodo(station.destino);
                TADArista tadarista = new TADArista(station.trafico);

                Arista arista = grafo.addArista(tadorigen, tadarista, taddestino);
                tadHash.addRuta(arista);
            }
        }
    }

    /* BACKUP RUTAS */
    public void addStation(TADNodo St){
        StationList.push_back(St);
        grafo.addNodo(St);
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

}