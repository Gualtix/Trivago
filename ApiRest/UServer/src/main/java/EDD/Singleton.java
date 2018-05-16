package EDD;

import EDD.arbolb.BTree;
import EDD.grafo.Grafo;
import EDD.hashTable.HashTable;
import EDD.list.List;
import EDD.tad.TADArbolB;
import EDD.tad.TADHash;

public class Singleton {

    private int idTicket;
    private BTree<TADArbolB> arbol;
    private Grafo grafo;
    private HashTable<TADHash> hash;

    private static Singleton instance = null;

    public static synchronized Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        
        return instance;
    }

    private Singleton() {
        idTicket = 200;
        arbol = new BTree<>(3);
        grafo = new Grafo();
        hash = new HashTable<>();
    }

    /* CONTADOR DE TICKETS */
    public int getIdTicket() {
        return idTicket;
    }

    public void incrementTicket() {
        idTicket++;
    }

    /* MANEJO DE ARBOL */
    public BTree<TADArbolB> getArbol() {
        return arbol;
    }

    /* MANEJO DE GRAFO */
    public Grafo getGrafo() {
        return grafo;
    }

    /* MANEJO DE TABLA HASH */
    public HashTable<TADHash> getHashTable() {
        return hash;
    }
}