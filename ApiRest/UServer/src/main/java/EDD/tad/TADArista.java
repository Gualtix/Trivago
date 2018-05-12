package EDD.tad;

public class TADArista implements Comparable {

    private double distancia;
    private double trafico;
    private double peso;

    public TADArista() {
    }

    public TADArista(double trafico) {
        this.trafico = trafico;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTrafico() {
        return trafico;
    }

    public void setTrafico(double trafico) {
        this.trafico = trafico;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void calcular(double lat1, double long1, double lat2, double long2) {
        int R = 6371;
        double dLat = getRad(lat2 - lat1);
        double dLon = getRad(long2 - long1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(getRad(lat1) * Math.cos(getRad(lat2))) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        distancia = R * c;
        peso = (distancia * trafico);
    }

    private double getRad(double deg) {
        return deg * (Math.PI / 180);
    }

    @Override
    public int compareTo(Object o) {
        TADArista value = (TADArista) o;
        return Double.compare(peso, value.getPeso());
    }

    @Override
    public boolean equals(Object obj) {
        TADArista value = (TADArista) obj;
        return peso == value.getPeso();
    }

    @Override
    public String toString() {
        return String.format("Distancia: %d\nTrafico: %d\n Peso: %d",
                distancia, trafico, peso);
    }
}
