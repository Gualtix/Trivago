package EDD;

import EDD.arbolb.BTree;
import EDD.grafo.Grafo;
import EDD.hashTable.HashTable;
import EDD.tad.TADArbolB;
import EDD.tad.TADHash;

public class Singleton {

    private int idTicket;
    private BTree<TADArbolB> arbol;
    private Grafo grafo;
    private HashTable<TADHash> hash;

    private static Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        
        return instance;
    }

    private Singleton() {
        idTicket = 200;
        arbol = new BTree<>(5);
        //grafo = new Grafo();
        //hash = new HashTable<>();
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void incrementTicket() {
        idTicket++;
    }

    public BTree<TADArbolB> getArbol() {
        return arbol;
    }
}