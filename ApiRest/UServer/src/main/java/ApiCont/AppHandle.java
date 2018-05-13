package ApiCont;

import EDD.Employee;
import EDD.Singleton;
import EDD.arbolb.BTree;
import EDD.tad.TADArbolB;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

    Singleton Sigi = Singleton.getInstance();

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

        Sigi.getArbol().graph("Arbolito");

        return TicketJs;
    }


    @GET
    @Path("/arbolito_img")
    @Produces("application/json")
    public String getRt(){

        String Base64_Tree = Sigi.getArbol().toBase64("Arbolito");

        String Rs = "{\"contenido\":\""+Base64_Tree+"   \"}";
        //String Base64_Tree = Sigi.getArbol().toBase64("Nomams");
        return Rs;
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

        Sigi.getArbol().graph("Arbolito");

        return TicketJs;
    }

}
