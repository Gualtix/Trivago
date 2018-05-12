package ApiCont;
import EDD.Employee;
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


@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

    BTree<TADArbolB> Ticket_Tree = new BTree<>(5);

    @GET
    @Path("/rutas")
    @Produces("application/json")
    public String getRt(){
        return "Rutas";
    }

    @GET
    @Path("/tickets")
    @Produces("application/json")
    public String getTickets(){
        Gson Gs = new Gson();
        Employee Emp = new Employee("WAaaaaalter",29,"waltix@gmail.com");
        String Ps = Gs.toJson(Emp);

        return Ps;
    }

    @POST
    @Path("/buyticket")
    @Produces("application/json")
    public String NewTicket(String TicketPrice){


        String TicketJs = "";
        Gson Gs = new Gson();
        TADArbolB Tk = Gs.fromJson(TicketPrice,TADArbolB.class);
        //Insertar Ticket al Arbol
        Ticket_Tree.add(Tk);

        TicketJs = Gs.toJson(Tk);

        //Retornat Ticket


        //Employee Emp = new Employee("Walter",29,"waltix@gmail.com");
        //String Ps = Gs.toJson(Emp);



        return TicketJs;
    }

}
