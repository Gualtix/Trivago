package EDD;

import EDD.arbolb.BTree;
import EDD.grafo.*;
import EDD.hashTable.HashTable;
import EDD.list.List;
import EDD.tad.*;
import com.google.gson.Gson;
import filesManager.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class Singleton {

    private int idTicket;
    private BTree<TADArbolB> arbol;
    private Grafo grafo;
    private HashTable<TADHash> hash;

    private List<TADNodo> StationList;
    private List<XRoute>  XRouteList;

    private List<Transaction_H> TransList;

    private static Singleton instance = null;

    private static final String FILENAME_RUTAS = "Rutas";
    private static final String FILENAME_GRAPH = "Mapa";
    private static final String FILENAME_CSV = "Reporte";

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
        TransList = new List<>();
    }

    public List<Transaction_H> getTransList(){
        return TransList;
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

    public JSONObject getStation(TADNodo data) {
        JSONObject object = new JSONObject();

        Nodo nodo = grafo.getNodo(data);
        data = (nodo != null) ? nodo.getData() : null;

        if (data != null) {
            object.put("codigo", data.getCodigo());
            object.put("nombre", data.getNombre());
            object.put("longitud", data.getLongitud());
            object.put("latitud", data.getLatitud());
        }

        return object;
    }

    /* MANEJO DE TABLA HASH */
    public HashTable<TADHash> getHashTable() {
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
                tadHash.setOrigen(grafo.getNodo(tadorigen));
                tadHash.addRuta(arista);
            }
            hash.put(tadHash);
        }
    }

    public JSONArray possibleRoutes(TADNodo station) {
        JSONArray array = new JSONArray();

        Iterator<XRoute> iterator = XRouteList.iterator();
        while (iterator.hasNext()) {
            XRoute next = iterator.next();

            Iterator<XStation> iterator1 = next.estaciones.iterator();
            while (iterator1.hasNext()) {
                XStation current = iterator1.next();

                if (current.destino == station.getCodigo() || current.origen == station.getCodigo()) {
                    JSONObject object = new JSONObject();
                    object.put("codigo", next.codigo);
                    object.put("nombre", next.nombre);
                    array.put(object);
                    break;
                }
            }
        }

        return array;
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

    public void backupRutas() {
        JSONArray array = new JSONArray();

        Iterator<XRoute> iterator = XRouteList.iterator();
        while (iterator.hasNext()) {
            XRoute current = iterator.next();
            array.put(new JSONObject(current.getJSON()));
        }

        FileManager fileManager = new FileManager(FILENAME_RUTAS, array.toString());
        fileManager.createFile("json");
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ L I S T   M A N A G E M E N T
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    public JSONArray getJson_RouteList(){
        JSONArray Reply = new JSONArray();

        Iterator<XRoute> iterator = XRouteList.iterator();
        while (iterator.hasNext()) {
            XRoute current = iterator.next();
            Reply.put(new JSONObject(current.getJSON()));
        }

        return Reply;
    }

    public String getJson_StationList(){

        String Reply = "[";

        Iterator<TADNodo> iterator = this.getStationList().iterator();
        while (iterator.hasNext()) {
            TADNodo current = iterator.next();
            Gson Gs = new Gson();
            String Js = Gs.toJson(current,TADNodo.class);
            Reply += Js;

            Reply += ",";
        }

        int Pos = Reply.length();
        StringBuilder Tmp = new StringBuilder(Reply);
        Tmp.deleteCharAt(Pos - 1);
        Reply = Tmp.toString();

        Reply += "]";

        return Reply;

    }

    /* GRAPHVIZ GRAFO - RUTAS */
    public void graphvizGraph() {
        String text = hash.graphMap();

        FileManager fileManager = new FileManager(FILENAME_GRAPH, text);
        fileManager.createFile(".dot");

        try {
            Runtime.getRuntime().exec(String.format("dot -Tpng %s.dot -o %s.png", FILENAME_GRAPH, FILENAME_GRAPH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shortRoute(TADNodo origen, TADNodo destino) {
        Ruta ruta = grafo.djkstra(origen, destino);
        String text = ruta.graph();

        FileManager fileManager = new FileManager(FILENAME_GRAPH, text);
        String original = fileManager.getFile(".dot");

        StringBuilder builder = new StringBuilder(original);
        builder.deleteCharAt(builder.lastIndexOf("}"));
        builder.append(text + "}");

        fileManager.setText(builder.toString());
        fileManager.createFile(".dot");

        try {
            Runtime.getRuntime().exec(String.format("dot -Tpng %s.dot -o %s.png", FILENAME_GRAPH, FILENAME_GRAPH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String reportCSV() {
        String text = "";
        StringBuilder builder = new StringBuilder(text);

        builder.append("\"REPORTE\",,,,,\n");
        builder.append(",,,,,\n");
        builder.append("\"ID_RUTA\",\"ID_TICKET\",\"ID_ESTACION\",\"VALOR_TRANSACCION\",\"FECHA\",\"HORA\"\n");

        Iterator<Transaction_H> iterator = TransList.iterator();
        while(iterator.hasNext()) {
            Transaction_H current = iterator.next();

            builder.append(current.getID_Route() + "\n");
            builder.append(current.getID_Ticket() + "\n");
            builder.append(current.getID_Station() + "\n");
            builder.append(current.getTransact_Value() + "\n");
            builder.append(current.getFecha() + "\n");
            builder.append(current.getHora() + "\n");
        }

        FileManager fileManager = new FileManager(FILENAME_CSV, text);
        fileManager.createFile(".csv");

        return fileManager.getFile(".csv");
    }
}