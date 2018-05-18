package EDD.grafo;


import EDD.list.List;
import EDD.tad.TADArista;
import EDD.tad.TADNodo;


import java.util.Iterator;

public class Grafo {

    private List<Nodo> nodos;

    public Grafo() {
        nodos = new List<>();
    }

    public Grafo(List<Nodo> nodos) {
        this.nodos = nodos;
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public Nodo addNodo(TADNodo value) {
        Nodo nodo = nodos.get(new Nodo(value));

        if (nodo == null) {
            nodo = new Nodo(value);
            nodos.push_back(nodo);
        }

        return nodo;
    }

    public Nodo addNodo(Nodo nodo) {
        nodos.push_back(nodo);

        return nodo;
    }

    public void loadStations(List<TADNodo> StList){
        Iterator<TADNodo> It = StList.iterator();
        while (It.hasNext()){
            TADNodo Tmp = It.next();
            this.addNodo(Tmp);
        }
    }

    public Nodo getNodo(TADNodo value) {
        return nodos.get(new Nodo(value));
    }

    public Arista addArista(TADNodo valueOrigen, TADArista valueArista, TADNodo valueDestino) {
        Nodo origen = nodos.get(new Nodo(valueOrigen));

        if (origen == null)
            nodos.push_back(new Nodo(valueOrigen));


        Nodo destino = nodos.get(new Nodo(valueDestino));

        if (destino == null) {
            nodos.push_back(new Nodo(valueDestino));
        }

        valueArista.calcular(origen.getData().getLatitud(), origen.getData().getLongitud(),
                destino.getData().getLatitud(), destino.getData().getLongitud());
        Arista arista = new Arista(valueArista, destino);

        if(origen.getAristas().get(arista) != null)
            return null;

        origen.addArista(arista);

        //return nodos.edit(origen);
        return arista;
    }

    public Ruta djkstra(TADNodo valueOrigen, TADNodo valueDestino) {
        Nodo origen = nodos.get(new Nodo(valueOrigen));
        Nodo destino = nodos.get(new Nodo(valueDestino));

        if (origen == null || destino == null)
            return null;

        List<Ruta> listaRuta = new List<>();

        Iterator<Arista> iterator = origen.getAristas().iterator();
        while (iterator.hasNext()) {
            Arista arista = iterator.next();
            Ruta ruta = new Ruta(arista);

            boolean res;

            if (arista.getDestino().equals(destino)) {
                listaRuta.push_back(ruta);
                res = true;
            } else
                res = djkstra(listaRuta, ruta, arista.getDestino(), destino);

            if (res) {
                if (!iterator.hasNext())
                    break;
            }
        }

        return rutaCorta(listaRuta);
    }

    private boolean djkstra(List<Ruta> listaRuta, Ruta rutaActual, Nodo nodoActual, Nodo destino) {
        boolean respuesta = true;
        Ruta rutaTemp = null;
        rutaTemp = (Ruta) rutaActual.clone();

        Iterator<Arista> iterator = nodoActual.getAristas().iterator();
        while (iterator.hasNext()) {
            Arista arista = iterator.next();

            if (rutaActual.getAristaList().get(arista) != null) {
                respuesta = false;
                break;
            }

            rutaActual.addArista(arista);

            if (arista.getDestino().equals(destino)) {
                listaRuta.push_back(rutaActual);
                respuesta = true;
                break;
            } else
                respuesta = djkstra(listaRuta, rutaActual, arista.getDestino(), destino);

            if (respuesta) {
                if (iterator.hasNext())
                    rutaActual = rutaTemp;
            } else
                rutaActual.removeArtista();
        }

        return respuesta;
    }

    private Ruta rutaCorta(List<Ruta> listaRuta) {
        Ruta ruta;

        Iterator<Ruta> iterator = listaRuta.iterator();
        ruta = iterator.next();
        while (iterator.hasNext()) {
            Ruta current = iterator.next();
            ruta = (current.getCosto() < ruta.getCosto()) ? current : ruta;
        }

        return ruta;
    }
}
