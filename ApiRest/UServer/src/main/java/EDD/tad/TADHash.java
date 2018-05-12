package EDD.tad;

import DAO.DataStructure;

public class TADHash extends DataStructure {

    private int id_ticket;
    private String codigo_verificacion;
    private String fecha_emision;
    private String hora_emision;
    private String fecha_devolucion;
    private double valor_inicial;
    private double saldo_disponible;

    public TADHash() {
    }

    public TADHash(int id_ticket, String codigo_verificacion, String fecha_emision, String hora_emision, String fecha_devolucion, double valor_inicial, double saldo_disponible) {
        this.id_ticket = id_ticket;
        this.codigo_verificacion = codigo_verificacion;
        this.fecha_emision = fecha_emision;
        this.hora_emision = hora_emision;
        this.fecha_devolucion = fecha_devolucion;
        this.valor_inicial = valor_inicial;
        this.saldo_disponible = saldo_disponible;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public String getCodigo_verificacion() {
        return codigo_verificacion;
    }

    public void setCodigo_verificacion(String codigo_verificacion) {
        this.codigo_verificacion = codigo_verificacion;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getHora_emision() {
        return hora_emision;
    }

    public void setHora_emision(String hora_emision) {
        this.hora_emision = hora_emision;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public double getValor_inicial() {
        return valor_inicial;
    }

    public void setValor_inicial(double valor_inicial) {
        this.valor_inicial = valor_inicial;
    }

    public double getSaldo_disponible() {
        return saldo_disponible;
    }

    public void setSaldo_disponible(double saldo_disponible) {
        this.saldo_disponible = saldo_disponible;
    }

    @Override
    public String toString() {
        return "id_ticket=" + id_ticket +
                "\nfecha_emision='" + fecha_emision +
                "\nvalor_inicial=" + valor_inicial +
                "\nsaldo_disponible=" + saldo_disponible;
    }

    @Override
    public String nodeName() {
        return String.format("node%s", this.hashCode());
    }

    @Override
    public String createNode() {
        return String.format("%s [label = \"\"]",
                nodeName());
    }

    @Override
    public int compareTo(Object o) {
        TADHash value = (TADHash) o;
        return Integer.compare(id_ticket, value.getId_ticket());
    }
}
