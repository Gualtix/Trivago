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
import java.util.Timer;
import java.util.concurrent.TimeUnit;

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
    private static final String FILENAME_SHORTROUTE = "ShortRoute";
    private static final String FILENAME_CSV = "Reporte";
    private static final String FILENAME_BACKUP_STATION = "Backup_Estaciones";
    private static final String FILENAME_BACKUP_RUTAS = "Backup_Rutas";
    private static final String FILENAME_BACKUP_TICKETS = "Backup_Tickets";
    private static final String FILENAME_BACKUP_TRANSACT = "Backup_Transacciones";

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

    /* GETTER Y SETTER */

    public List<Transaction_H> getTransList(){
        return TransList;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public BTree<TADArbolB> getArbol() {
        return arbol;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public HashTable<TADHash> getHashTable() {
        return hash;
    }

    /* CONTADOR DE TICKETS */

    public void incrementTicket() {
        idTicket++;
    }

    /* MANEJO DE GRAFO */

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

    public void updateHash(TADHash oldData, TADHash newData) {
        hash.pop(oldData);
        newData.setOrigen(oldData.getOrigen());
        newData.setEstaciones(oldData.getEstaciones());
        hash.put(newData);
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

    public List<TADNodo> getStationList(){
        return StationList;
    }

    public void addXRoute(XRoute XRt){
        XRouteList.push_back(XRt);
    }

    public List<XRoute> getXRouteList(){
        return XRouteList;
    }


    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ B A C K U P ' S  &  L O A D
    public void backupRutas() {
        JSONArray text = getJson_RouteList();

        FileManager fileManager = new FileManager(FILENAME_BACKUP_RUTAS, text.toString());
        fileManager.createFile(".json");
        fileManager.encryptFile();
    }

    public void backupStation() {
        String text = getJson_StationList();

        FileManager fileManager = new FileManager(FILENAME_BACKUP_STATION, text);
        fileManager.createFile(".json");
        fileManager.encryptFile();
    }

    public void loadStation() {
        FileManager fileManager = new FileManager(FILENAME_BACKUP_STATION);
        String text = fileManager.decryptFile();

        JSONArray jsa = new JSONArray(text);
        for (int i = 0; i < jsa.length(); i++) {
            JSONObject jso = jsa.getJSONObject(i);
            TADNodo nodo = new TADNodo();
            nodo.setCodigo(jso.getInt("codigo"));
            nodo.setNombre(jso.getString("nombre"));
            nodo.setLatitud(jso.getDouble("latitud"));
            nodo.setLongitud(jso.getDouble("longitud"));

            grafo.addNodo(nodo);
            StationList.push_front(nodo);
        }
    }

    public void addStation(TADNodo St){
        StationList.push_back(St);
        grafo.addNodo(St);
    }

    public void loadRutas() {
        FileManager fileManager = new FileManager(FILENAME_BACKUP_RUTAS);
        String text = fileManager.decryptFile();

        JSONArray jsa = new JSONArray(text);
        for (int i = 0; i < jsa.length(); i++) {
            JSONObject jso = jsa.getJSONObject(i);

            int codigo = jso.getInt("codigo");
            String nombre = jso.getString("nombre");
            String color = jso.getString("color");
            double precio = jso.getDouble("precio");

            XRoute route = new XRoute(codigo, nombre, color, precio);
            XRouteList.push_front(route);

            JSONArray jsaE = jso.getJSONArray("estaciones");
            for (int j = 0; j < jsaE.length(); j++) {
                JSONObject jsoE = jsaE.getJSONObject(j);
                int origen = jsoE.getInt("origen");
                int destino = jsoE.getInt("destino");
                int trafico = jsoE.getInt("trafico");

                XStation station = new XStation(origen, destino, trafico);
                route.estaciones.push_back(station);
            }
        }

        fillHashTable();
    }

    public void backupTransacciones() {
        JSONArray jsa = new JSONArray();

        Iterator<Transaction_H> iterator = TransList.iterator();
        while (iterator.hasNext()) {
            Transaction_H current = iterator.next();

            jsa.put(current.getJSON());
        }

        FileManager fileManager = new FileManager(FILENAME_BACKUP_TRANSACT, jsa.toString());
        fileManager.createFile(".json");
        fileManager.encryptFile();
    }

    public void loadTransaction() {
        FileManager fileManager = new FileManager(FILENAME_BACKUP_TRANSACT);
        String text = fileManager.decryptFile();

        JSONArray jsa = new JSONArray(text);
        for (int i = 0; i < jsa.length(); i++) {
            JSONObject jso = jsa.getJSONObject(i);
            Transaction_H transaction_h = new Transaction_H();
            transaction_h.setID_Route(jso.getInt("id_route"));
            transaction_h.setID_Station(jso.getInt("id_ticket"));
            transaction_h.setID_Ticket(jso.getInt("id_station"));
            transaction_h.setTransact_Value(jso.getDouble("transact_value"));

            TransList.push_back(transaction_h);
        }
    }

    public void backupTickets() {
        JSONArray jsa = arbol.toJSON();

        FileManager fileManager = new FileManager(FILENAME_BACKUP_TICKETS, jsa.toString());
        fileManager.createFile(".json");
        fileManager.encryptFile();
    }

    public void loadTickets() {
        FileManager fileManager = new FileManager(FILENAME_BACKUP_TICKETS);
        String text = fileManager.decryptFile();

        JSONArray jsa = new JSONArray(text);
        for (int i = 0; i < jsa.length(); i++) {
            JSONObject jso = jsa.getJSONObject(i);
            TADArbolB tadArbolB = new TADArbolB(arbol.getK());
            tadArbolB.setCodigo(jso.getInt("codigo"));
            tadArbolB.setVerificacion(jso.getString("verificacion"));
            tadArbolB.setEmision(jso.getString("emision"));
            tadArbolB.setDevolucion(jso.getString("devolucion"));
            tadArbolB.setValor(jso.getDouble("valor"));
            tadArbolB.setSaldo(jso.getDouble("saldo"));

            arbol.add(tadArbolB);
        }
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ L I S T   M A N A G E M E N T
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    public XRoute getRouteByID(int ID){

        XRoute Tmp = new XRoute();
        Iterator<XRoute> iterator = XRouteList.iterator();
        while (iterator.hasNext()) {
            XRoute current = iterator.next();
            if(current.codigo == ID){
                return current;
            }
        }

        return null;
    }

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
    public String graphvizGraph() {
        String text = hash.graphMap();

        FileManager fileManager = new FileManager(FILENAME_GRAPH, text);
        fileManager.createFile(".dot");

        try {
            Runtime.getRuntime().exec(String.format("dot -Tpng %s.dot -o %s.png", FILENAME_GRAPH, FILENAME_GRAPH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileManager.getImageBase64(".png");
    }

    public String shortRouteGraph(TADNodo origen, TADNodo destino) {
        Ruta ruta = grafo.djkstra(origen, destino);
        String text = ruta.graph();

        FileManager fileManager = new FileManager(FILENAME_GRAPH, text);
        String original = fileManager.getFile(".dot");

        StringBuilder builder = new StringBuilder(original);
        builder.deleteCharAt(builder.lastIndexOf("}"));
        builder.append(text + "}");

        fileManager.setFilename(FILENAME_SHORTROUTE);
        fileManager.setText(builder.toString());
        fileManager.createFile(".dot");

        try {
            Runtime.getRuntime().exec(String.format("dot -Tpng %s.dot -o %s.png", FILENAME_SHORTROUTE, FILENAME_SHORTROUTE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer Tm = new Timer();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return fileManager.getImageBase64(".png");
    }

    public JSONArray shortRoute(TADNodo origen, TADNodo destino) {

        Ruta ruta = grafo.djkstra(origen, destino);
        JSONArray jsa = new JSONArray(ruta.getJSON());

        return jsa;
    }

    /* REPORTE */
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