package ApiCont;

import EDD.Singleton;
import EDD.grafo.XRoute;
import EDD.grafo.XStation;
import EDD.tad.TADArbolB;
import EDD.tad.TADArista;
import EDD.tad.TADNodo;
import com.google.gson.Gson;

import javax.swing.text.TabableView;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

import org.json.*;

@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

    Singleton Sigi = Singleton.getInstance();

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ getStations
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    @GET
    @Path("/getstations")
    @Produces("application/json")
    public String getStations(){

        if(Sigi.getStationList().isEmpty()){
            StationsLoader();
        }

        String Rs = "[";

        Iterator<TADNodo> iterator = Sigi.getStationList().iterator();
        while (iterator.hasNext()) {
            TADNodo current = iterator.next();
            Gson Gs = new Gson();
            String Js = Gs.toJson(current,TADNodo.class);
            Rs += Js;

            Rs += ",";
        }

        int Pos = Rs.length();
        StringBuilder Tmp = new StringBuilder(Rs);
        Tmp.deleteCharAt(Pos - 1);
        Rs = Tmp.toString();

        Rs += "]";

        return Rs;
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ GetAvailableStations
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

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

        return TicketJs;
    }

    @GET
    @Path("/arbolito_img")
    @Produces("application/json")
    public String getRt(){

        String Base64_Tree = Sigi.getArbol().toBase64();

        String Rs = "{\"contenido\":\""+Base64_Tree+"   \"}";
        //String Base64_Tree = Sigi.getArbol().toBase64("Nomams");
        return Rs;
    }

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

        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //LocalDate localDate = LocalDate.now();
        //System.out.println(dtf.format(localDate)); //2016/11/16

        Sigi.getArbol().add(NewTicket);
        //Sigi.getArbol().(NewTicket);

        TicketJs = Gs.toJson(NewTicket);

        //Retornat Ticket
        //Employee Emp = new Employee("Walter",29,"waltix@gmail.com");
        //String Ps = Gs.toJson(Emp);

        Sigi.getArbol().graph();

        return TicketJs;
    }

    @GET
    @Path("/rutas")
    @Produces("application/json")
    public String getTickets(){
        Gson Gs = new Gson();
        //Employee Emp = new Employee("WAaaaaalter",29,"waltix@gmail.com");
        //String Ps = Gs.toJson(Emp);

        return "Hola";
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ Add New Route
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

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

    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ Add New Station
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    @POST
    @Path("/add_new_station")
    @Produces("application/json")
    public void newStation(String St){
        Gson gson = new Gson();

        TADNodo StInfo = gson.fromJson(St, TADNodo.class);
        Sigi.addStation(StInfo);

    }

    @POST
    @Path("/addstation")
    @Produces("application/json")
    public void addStation(String St){

        /*
        int k = 0;
        //Gson gson = new Gson();
        //TADNodo nodo = gson.fromJson(station, TADNodo.class);
        //------------------- Nueva Estacion

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
        */

        Sigi.getGrafo().loadStations(Sigi.getStationList());

        String RutaJs = "";

        //Ruta 203
        Sigi.getGrafo().addArista(new TADNodo(300),new TADArista(1),new TADNodo(306));
        Sigi.getGrafo().addArista(new TADNodo(306),new TADArista(1),new TADNodo(303));
        Sigi.getGrafo().addArista(new TADNodo(303),new TADArista(1),new TADNodo(307));
        Sigi.getGrafo().addArista(new TADNodo(307),new TADArista(1),new TADNodo(304));
        Sigi.getGrafo().addArista(new TADNodo(304),new TADArista(1),new TADNodo(308));

        //Ruta 204
        Sigi.getGrafo().addArista(new TADNodo(309),new TADArista(1),new TADNodo(301));
        Sigi.getGrafo().addArista(new TADNodo(301),new TADArista(1),new TADNodo(305));
        Sigi.getGrafo().addArista(new TADNodo(305),new TADArista(1),new TADNodo(302));
        Sigi.getGrafo().addArista(new TADNodo(302),new TADArista(1),new TADNodo(308));
        Sigi.getGrafo().addArista(new TADNodo(308),new TADArista(1),new TADNodo(303));

        //Ruta 205
        Sigi.getGrafo().addArista(new TADNodo(300),new TADArista(1),new TADNodo(302));
        Sigi.getGrafo().addArista(new TADNodo(302),new TADArista(1),new TADNodo(309));

        //Ruta 206
        Sigi.getGrafo().addArista(new TADNodo(300),new TADArista(1),new TADNodo(309));

        //Ruta 207
        Sigi.getGrafo().addArista(new TADNodo(303),new TADArista(1),new TADNodo(309));

        //RUta 208
        Sigi.getGrafo().addArista(new TADNodo(303),new TADArista(1),new TADNodo(304));

    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ Loader
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    public void StationsLoader(){
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
    }

    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............
    //(^< ............ ............ ............ ............ ............ C + +
    //(^< ............ ............ ............ ............ ............ ............ ............ ............ ............ ............

    //(^< ............ ............ ............ ............ ............ getList_of_Routes_that_pass_through_a_Station
    @GET
    @Path("/getList_of_Routes_that_pass_through_a_Station")
    @Produces("application/json")
    public String getList_of_Stations_that_pass_through_a_point(){
        String Reply = "";

        return Reply;
    }

    //(^< ............ ............ ............ ............ ............ askIfStationExists
    @GET
    @Path("/askIfStationExists")
    @Produces("application/json")
    public String askIfStationExists(String St){
        String Reply = "";

        Gson Gs = new Gson();
        TADNodo TmpStation = Gs.fromJson(St,TADNodo.class);

        TADNodo Fd = Sigi.getGrafo().getNodo(TmpStation).getData();
        if(Fd == null){
            Reply = "{}";
        }
        else{
            Reply = Gs.toJson(Fd);
        }
        return Reply;
    }
}
