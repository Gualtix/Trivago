package ApiCont;
import EDD.Employee;
import com.google.gson.Gson;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@Path("/urban")
public class AppHandle extends Application {

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

}
