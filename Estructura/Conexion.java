package Estructura;

/**
 * Borde, conexion, camino, vertice o como quiera llamarsele
 * 
 * @author live y edgerik
 */
public class Conexion  
{
    //Variables globales
    private final String id;
    private final NodoGrafo origen;
    private final NodoGrafo destino;
    private final double distancia;
    //Constructor
    public Conexion(String id, NodoGrafo source, NodoGrafo destination, double weight) {
        this.id = id;
        this.origen = source;
        this.destino = destination;
        this.distancia = weight;
    }
    //Gets and sets
    public String getId() {
        return id;
    }
    public NodoGrafo getDestino() {
        return destino;
    }
    
    public NodoGrafo getOrigen() {
        return origen;
    }
    public double getDistancia() {
        return distancia;
    }
    @Override
    public String toString() {
        return origen.toString() + "--" + distancia + "-->" + destino.toString();
    }
}