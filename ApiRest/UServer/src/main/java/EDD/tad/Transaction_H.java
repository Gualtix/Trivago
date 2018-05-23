package EDD.tad;

import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Transaction_H implements Comparable {

    int ID_Route;
    int ID_Ticket;
    int ID_Station;
    double Transact_Value;

    String Fecha;
    String Hora;

    Date ObjetoFecha;

    public Transaction_H(int ID_Route,int ID_Ticket,int ID_Station,double Transact_Value) {

        this.ID_Route = ID_Route;
        this.ID_Ticket = ID_Ticket;
        this.ID_Station = ID_Station;
        this.Transact_Value = Transact_Value;

        Fecha = getCurrentDate();
        Hora = getCurrentHour();

        ObjetoFecha = new Date();
    }

    public Transaction_H() {

    }

    public int getID_Route() {
        return ID_Route;
    }

    public int getID_Ticket() {
        return ID_Ticket;
    }

    public int getID_Station() {
        return ID_Station;
    }

    public double getTransact_Value() {
        return Transact_Value;
    }

    public String getFecha() {
        return Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public String getCurrentDate(){

        //Fecha
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/LLLL/yyyy");
        String MyDate = localDate.format(formatter);

        return MyDate;
    }

    public String getCurrentHour(){

        Date MyDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(MyDate);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);

        String Rp = Integer.toString(hours)+":"+Integer.toString(minutes)+":"+Integer.toString(seconds);
        return Rp;
    }

    public void setID_Route(int ID_Route) {
        this.ID_Route = ID_Route;
    }

    public void setID_Ticket(int ID_Ticket) {
        this.ID_Ticket = ID_Ticket;
    }

    public void setID_Station(int ID_Station) {
        this.ID_Station = ID_Station;
    }

    public void setTransact_Value(double transact_Value) {
        Transact_Value = transact_Value;
    }

    @Override
    public int compareTo(Object o){
        Date InDATE = (Date)o;

        if(this.ObjetoFecha.after(InDATE)) {
            return 1;
        }
        if(this.ObjetoFecha.before(InDATE))
            return  -1;

        return  0;
    }

    public JSONObject getJSON() {
        JSONObject jso = new JSONObject();

        jso.put("id_route", ID_Route);
        jso.put("id_ticket", ID_Ticket);
        jso.put("id_station", ID_Station);
        jso.put("transact_value", Transact_Value);

        return jso;
    }
}
