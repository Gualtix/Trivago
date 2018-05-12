package EDD.tad;

import DAO.DataStructure;

public class TADArbolB extends DataStructure {
    private int codigo;
    private String verificacion;
    private String emision;
    private String devolucion;
    private double valor;
    private double saldo;

    public TADArbolB(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }

    public String getEmision() {
        return emision;
    }

    public void setEmision(String emision) {
        this.emision = emision;
    }

    public String getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(String devolucion) {
        this.devolucion = devolucion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return String.format("%d\\n%s\\n%s\\n%d\\n%d",
                codigo, verificacion, emision, valor, saldo);
    }

    @Override
    public String nodeName() {
        return "";
    }

    @Override
    public String createNode() {
        return "";
    }

    @Override
    public String getJSON() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        TADArbolB value = (TADArbolB) o;
        return Integer.compare(codigo, value.getcodigo());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
