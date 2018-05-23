package ApiCont;

import EDD.Singleton;
import EDD.grafo.XRoute;
import EDD.grafo.XStation;
import EDD.tad.*;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.*;

@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

    Singleton Sigi = Singleton.getInstance();

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ W E B   A P P
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............


    //(^< ............ ............ ............ ............ ............ default_path
    @GET
    @Path("/default_path")
    @Produces("application/json")
    public String default_path(){
        Sigi.loadStation();
        Sigi.loadRutas();
        Sigi.loadTickets();
        Sigi.loadTransaction();
        return "U R B A N   W E B   S E R V E R";
    }

    //(^< ............ ............ ............ ............ ............ add_new_station
    @POST
    @Path("/add_new_station")
    @Produces("application/json")
    public String newStation(String St){

        Gson gson = new Gson();

        TADNodo StInfo = gson.fromJson(St, TADNodo.class);
        Sigi.addStation(StInfo);

        Sigi.backupStation();
        return "{\"mensaje\":\"Ok\"}";
    }

    //(^< ............ ............ ............ ............ ............ update_station
    @POST
    @Path("/update_station")
    @Produces("application/json")
    public String update_station(String St){
        Gson gson = new Gson();

        TADNodo StInfo = gson.fromJson(St, TADNodo.class);

        TADNodo Tmp = Sigi.getStationList().get(StInfo);

        Tmp.setNombre(StInfo.getNombre());
        Tmp.setLatitud(StInfo.getLatitud());
        Tmp.setLongitud(StInfo.getLongitud());

        Sigi.backupStation();

        return "{\"mensaje\":\"Ok\"}";
    }

    //(^< ............ ............ ............ ............ ............ getstations
    @GET
    @Path("/getstations")
    @Produces("application/json")
    public String getStations(){

        String Reply = Sigi.getJson_StationList();

        return Reply;
    }


    //(^< ............ ............ ............ ............ ............ add_new_route
    @POST
    @Path("/add_new_route")
    @Produces("application/json")
    public void newRoute(String Rt){

        XRoute Tmp = new XRoute();

        JSONObject obj = new JSONObject(Rt);
        int codigo = obj.getInt("codigo");
        String nombre = obj.getString("nombre");
        String color = obj.getString("color");
        double precio = obj.getDouble("precio");

        Tmp.codigo = codigo;
        Tmp.nombre = nombre;
        Tmp.color = color;
        Tmp.precio = precio;


        JSONArray arr = obj.getJSONArray("estaciones");

        for (int i = 0; i < arr.length(); i++)
        {
            XStation Ts = new XStation();
            JSONObject obj2 = arr.getJSONObject(i);
            int Org = obj2.getInt("origen");
            int Dest = obj2.getInt("destino");
            double Traf = obj2.getDouble("trafico");

            Ts.origen = Org;
            Ts.destino = Dest;
            Ts.trafico = Traf;

            Tmp.estaciones.push_back(Ts);
        }

        Sigi.addXRoute(Tmp);
        Sigi.backupRutas();
    }

    //(^< ............ ............ ............ ............ ............ update_route
    @POST
    @Path("/update_route")
    @Produces("application/json")
    public String update_route(String Rt_Up){

        JSONObject Js = new JSONObject(Rt_Up);

        int ID_RT = Js.getInt("codigo");

        String Nombre = Js.getString("nombre");
        double Precio  = Js.getDouble("precio");
        String Color = Js.getString("color");

        XRoute Tmp = Sigi.getRouteByID(ID_RT);

        String OldName = Tmp.nombre;

        Tmp.nombre = Nombre;
        Tmp.precio = Precio;
        Tmp.color = Color;

        Sigi.updateHash(new TADHash(0,Nombre),new TADHash(ID_RT,Nombre,Color,Precio));

        Sigi.backupRutas();
        return "{\"mensaje\":\"Ok\"}";
    }

    //(^< ............ ............ ............ ............ ............ getroutes
    @GET
    @Path("/getroutes")
    @Produces("application/json")
    public String getTickets(){

        if(Sigi.getStationList().isEmpty()){
            StationLoader();
        }

        String Reply = "";

        Reply = Sigi.getJson_RouteList().toString();

        return  Reply;
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ S H O R T E S T   R O U T E
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............



    @POST
    @Path("/getshortestroute")
    @Produces("application/json")
    public String getshortestroute(String Points){
        JSONObject Js = new JSONObject(Points);

        int Origen_ID = Js.getInt("origen");
        int Destino_ID = Js.getInt("destino");

        JSONArray jsa = Sigi.shortRoute(new TADNodo(Origen_ID),new TADNodo(Destino_ID));
        return jsa.toString();
    }


    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ B A S E 6 4   I M G   M O D E L S
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    //(^< ............ ............ ............ ............ ............ arbolito_img
    @GET
    @Path("/arbolito_img")
    @Produces("application/json")
    public String get_arbolito_img(){
        String Base64_Tree = Sigi.getArbol().toBase64();
        String Rs = "{\"contenido\":\""+Base64_Tree+"\"}";
        return Rs;
    }

    //(^< ............ ............ ............ ............ ............ tabla_hash_img
    @GET
    @Path("/tabla_hash_img")
    @Produces("application/json")
    public String get_tabla_hash_img(){
        String Base64_Hash = Sigi.getHashTable().toBase64();
        String Rs = "{\"contenido\":\""+Base64_Hash+"\"}";
        return Rs;
    }

    //(^< ............ ............ ............ ............ ............ grafo_img
    @GET
    @Path("/grafo_img")
    @Produces("application/json")
    public String get_grafo_img(){
        String Base64_Graph = Sigi.graphvizGraph();

        if(Base64_Graph != null){
            return "{\"contenido\":\""+Base64_Graph+"\"}";
        }
        else{
            return "{\"contenido\":\"null\"}";
        }
    }

    //(^< ............ ............ ............ ............ ............ getshortestroute_img
    @POST
    @Path("/getshortestroute_img")
    @Produces("application/json")
    public String getshortestroute_img(String Points){

        JSONObject Js = new JSONObject(Points);

        int Origen_ID = Js.getInt("origen");
        int Destino_ID = Js.getInt("destino");

        String Base64_Graph = Sigi.shortRouteGraph(new TADNodo(Origen_ID),new TADNodo(Destino_ID));

        if(Base64_Graph != null){
            return "{\"contenido\":\""+Base64_Graph+"\"}";
        }
        else{
            return "{\"contenido\":\"null\"}";
        }
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ Reporte
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ reporte csv
    @GET
    @Path("/report_csv")
    @Produces("text/csv")
    public String get_report_csv(){
        String report = Sigi.reportCSV();
        return report;
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ C + +
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    //(^< ............ ............ ............ ............ ............ getList_of_Routes_that_pass_through_a_Station
    @POST
    @Path("/getList_of_Routes_that_pass_through_a_Station")
    @Produces("application/json")
    public String getList_of_Stations_that_pass_through_a_point(String St){
        String Reply = "";

        Gson Gs = new Gson();
        TADNodo TmpStation = Gs.fromJson(St,TADNodo.class);

        JSONArray Jr = Sigi.possibleRoutes(TmpStation);

        Reply = Jr.toString();
        return Reply;
    }

    //(^< ............ ............ ............ ............ ............ askIfStationExists
    @POST
    @Path("/askIfStationExists")
    @Produces("application/json")
    public String askIfStationExists(String St){
        String Reply = "";

        Gson Gs = new Gson();
        TADNodo TmpStation = Gs.fromJson(St,TADNodo.class);

        JSONObject Fd = Sigi.getStation(TmpStation);
        Reply = Fd.toString();
        return Reply;
    }


    //(^< ............ ............ ............ ............ ............ buy_urban_ride
    @PUT
    @Path("/buy_urban_ride")
    @Produces("application/json")
    public String buy_urban_ride(String Tk){

        JSONObject Ob = new JSONObject(Tk);

        int codRuta = Ob.getInt("codigo_ruta");
        String ruta = Ob.getString("ruta");
        int Ticket = Ob.getInt("ticket");
        int Estacion = Ob.getInt("estacion");

        TADArbolB Tmp = Sigi.getArbol().get(new TADArbolB(Ticket));

        if(Tmp == null){
            return "{}";
        }

        TADHash Hs = Sigi.getHashTable().get(new TADHash(codRuta, ruta));

        double PrecioRuta = Hs.getPrecio();
        double SaldoDisponible = Tmp.getSaldo();

        if(Tmp.getSaldo() >= PrecioRuta){
            Tmp.setSaldo(SaldoDisponible - PrecioRuta);
            Sigi.getTransList().push_back(new Transaction_H(codRuta,Ticket,Estacion,PrecioRuta));

            Sigi.getArbol().graph();
            Sigi.backupTickets();
            Sigi.backupTransacciones();
            return "{\"le_alcanza\":true}";
        }
        else{
            return "{\"le_alcanza\":false}";
        }
    }

    //(^< ............ ............ ............ ............ ............ devolucion

    @PUT
    @Path("/devolucion")
    @Produces("application/json")
    public String updateTicket(String Tkk){

        String TicketPrice = Tkk;
        String TicketJs = "";
        Gson Gs = new Gson();
        TADArbolB Tk = Gs.fromJson(TicketPrice,TADArbolB.class);

        TADArbolB NewTicket = Sigi.getArbol().get(Tk);

        if(NewTicket == null){
            return "{\"estado\":true}";
        }

        //Fecha
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String formattedString = localDate.format(formatter);

        NewTicket.setDevolucion(formattedString);

        //Valor Actual
        NewTicket.setSaldo(0);
        TicketJs = Gs.toJson(NewTicket);

        Sigi.getArbol().graph();
        Sigi.backupTickets();

        return TicketJs;
    }

    //(^< ............ ............ ............ ............ ............ buyticket
    @POST
    @Path("/buyticket")
    @Produces("application/json")
    public String NewTicket(String TicketPrice){

        String TicketJs = "";
        Gson Gs = new Gson();
        TADArbolB Tk = Gs.fromJson(TicketPrice,TADArbolB.class);

        //Insertar Ticket al Arbol
        Sigi.incrementTicket();
        TADArbolB NewTicket = new TADArbolB(Sigi.getIdTicket());

        //Fecha
        LocalDate localDate = LocalDate.now();//For reference
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String formattedString = localDate.format(formatter);

        NewTicket.setEmision(formattedString);

        //Valor
        NewTicket.setValor(Tk.getValor());

        //Valor Actual
        NewTicket.setSaldo(Tk.getValor());

        Sigi.getArbol().add(NewTicket);

        TicketJs = Gs.toJson(NewTicket);
        Sigi.getArbol().graph();
        Sigi.backupTickets();

        return TicketJs;
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ Loader
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    //(^< ............ ............ ............ ............ ............ RouteLoader
    public void RouteLoader(){

        //Sigi.getGrafo().loadStations(Sigi.getStationList());

        //Ruta 203
        /*
        Sigi.getGrafo().addArista(new TADNodo(300),new TADArista(1),new TADNodo(306));
        Sigi.getGrafo().addArista(new TADNodo(306),new TADArista(1),new TADNodo(303));
        Sigi.getGrafo().addArista(new TADNodo(303),new TADArista(1),new TADNodo(307));
        Sigi.getGrafo().addArista(new TADNodo(307),new TADArista(1),new TADNodo(304));
        Sigi.getGrafo().addArista(new TADNodo(304),new TADArista(1),new TADNodo(308));
        */

        XRoute R_0 = new XRoute(0,"Rojo","#473d3d",32.50);

        R_0.estaciones.push_front(new XStation(0,6,1));
        R_0.estaciones.push_front(new XStation(6,3,1));
        R_0.estaciones.push_front(new XStation(3,7,1));
        R_0.estaciones.push_front(new XStation(7,4,1));
        R_0.estaciones.push_front(new XStation(4,8,1));

        XRoute R_1 = new XRoute(1,"Azul","#3069e5",32.50);

        R_1.estaciones.push_front(new XStation(9,1,1));
        R_1.estaciones.push_front(new XStation(1,5,1));
        R_1.estaciones.push_front(new XStation(5,2,1));
        R_1.estaciones.push_front(new XStation(2,8,1));
        R_1.estaciones.push_front(new XStation(8,3,1));


        XRoute R_2 = new XRoute(2,"Verde","#45d326",32.50);


        R_2.estaciones.push_front(new XStation(0,2,1));
        R_2.estaciones.push_front(new XStation(2,9,1));

        XRoute R_3 = new XRoute(3,"Amarillo","#f9db31",32.50);
        R_3.estaciones.push_front(new XStation(0,9,1));

        XRoute R_4 = new XRoute(4,"Celeste","#6bd3f9",32.50);
        R_4.estaciones.push_front(new XStation(3,9,1));

        XRoute R_5 = new XRoute(5,"Morado","#b254ff",32.50);
        R_5.estaciones.push_front(new XStation(3,4,1));

        Sigi.getXRouteList().push_front(R_0);
        Sigi.getXRouteList().push_front(R_1);
        Sigi.getXRouteList().push_front(R_2);
        Sigi.getXRouteList().push_front(R_3);
        Sigi.getXRouteList().push_front(R_4);
        Sigi.getXRouteList().push_front(R_5);
    }

    //(^< ............ ............ ............ ............ ............ StationLoader
    public void StationLoader(){
        Gson gson = new Gson();

        String St_0 = "{\n" +
                "  \"codigo\":0,\n" +
                "  \"nombre\":\"SanCarlos\",\n" +
                "  \"latitud\":14.587910334,\n" +
                "  \"longitud\":-90.55185482\n" +
                "}";

        String St_1 = "{\n" +
                "  \"codigo\":1,\n" +
                "  \"nombre\":\"Tikal\",\n" +
                "  \"latitud\":14.598263997,\n" +
                "  \"longitud\":-90.54333365\n" +
                "}";


        String St_2 = "{\n" +
                "  \"codigo\":2,\n" +
                "  \"nombre\":\"Recoleccion\",\n" +
                "  \"latitud\":14.583486002,\n" +
                "  \"longitud\":-90.54154280\n" +
                "}";

        String St_3 = "{\n" +
                "  \"codigo\":3,\n" +
                "  \"nombre\":\"Antigua\",\n" +
                "  \"latitud\":14.577194855,\n" +
                "  \"longitud\":-90.55962250\n" +
                "}";

        String St_4 = "{\n" +
                "  \"codigo\":4,\n" +
                "  \"nombre\":\"Miraflores\",\n" +
                "  \"latitud\":14.570307820,\n" +
                "  \"longitud\":-90.55095360\n" +
                "}";

        String St_5 = "{\n" +
                "  \"codigo\":5,\n" +
                "  \"nombre\":\"Castellana\",\n" +
                "  \"latitud\":14.593571069,\n" +
                "  \"longitud\":-90.53839839\n" +
                "}";

        String St_6 = "{\n" +
                "  \"codigo\":6,\n" +
                "  \"nombre\":\"Reforma\",\n" +
                "  \"latitud\":14.582821478,\n" +
                "  \"longitud\":-90.55107001\n" +
                "}";

        String St_7 = "{\n" +
                "  \"codigo\":7,\n" +
                "  \"nombre\":\"Alameda\",\n" +
                "  \"latitud\":14.567394490,\n" +
                "  \"longitud\":-90.56370946\n" +
                "}";

        String St_8 = "{\n" +
                "  \"codigo\":8,\n" +
                "  \"nombre\":\"Embajada\",\n" +
                "  \"latitud\":14.573957036,\n" +
                "  \"longitud\":-90.53980235\n" +
                "}";

        String St_9 = "{\n" +
                "  \"codigo\":9,\n" +
                "  \"nombre\":\"Trebol\",\n" +
                "  \"latitud\":14.604334424,\n" +
                "  \"longitud\":-90.55209998\n" +
                "}";

        TADNodo nodo_0 = gson.fromJson(St_0, TADNodo.class);
        TADNodo nodo_1 = gson.fromJson(St_1, TADNodo.class);
        TADNodo nodo_2 = gson.fromJson(St_2, TADNodo.class);
        TADNodo nodo_3 = gson.fromJson(St_3, TADNodo.class);
        TADNodo nodo_4 = gson.fromJson(St_4, TADNodo.class);
        TADNodo nodo_5 = gson.fromJson(St_5, TADNodo.class);
        TADNodo nodo_6 = gson.fromJson(St_6, TADNodo.class);
        TADNodo nodo_7 = gson.fromJson(St_7, TADNodo.class);
        TADNodo nodo_8 = gson.fromJson(St_8, TADNodo.class);
        TADNodo nodo_9 = gson.fromJson(St_9, TADNodo.class);

        Sigi.addStation(nodo_0);
        Sigi.addStation(nodo_1);
        Sigi.addStation(nodo_2);
        Sigi.addStation(nodo_3);
        Sigi.addStation(nodo_4);
        Sigi.addStation(nodo_5);
        Sigi.addStation(nodo_6);
        Sigi.addStation(nodo_7);
        Sigi.addStation(nodo_8);
        Sigi.addStation(nodo_9);

        RouteLoader();

        Sigi.fillHashTable();

    }
}


