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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

    Singleton Sigi = Singleton.getInstance();
    //BTree<TADArbolB> Ticket_Tree = new BTree<TADArbolB>(5);
    //int ID_Ticket = 200;
    //Employee ID_Ticket = Employee.getInstance();

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

        Sigi.getArbol().graph();

        return TicketJs;
    }

}
