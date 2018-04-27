package Estructura;
//BiBliotecas a usar
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 *
 * @author live y edgerik
 */
public class Dijkstra 
{
    //Variables globales
    private List<NodoGrafo> nodos;
    private List<Conexion> bordes;
    private Set<NodoGrafo> nodosListos;
    private Set<NodoGrafo> nodosNoListos;
    private Map<NodoGrafo, NodoGrafo> predecesores;
    private Map<NodoGrafo, Double> distancia;
    /**
     * Constructor, Le pasa el grafo al dijkstra
     * @param graph El grafo a manejar
     */
    public Dijkstra(Grafo graph)
    {
        this.nodos = new ArrayList<>(graph.getNodos());
        this.bordes = new ArrayList<>(graph.getConexiones());
    }
    /**
     *  Constructor, Crea un dijkstra para luego darle el grafo y buscar rutas
     */
    public Dijkstra() 
    {    
        this.nodos = null;
        this.bordes = null;
    }
    /**
     * Busca rutas
     * @param origen Nodo del cual se empezará 
     */
    public void buscarRutas(NodoGrafo origen) 
    {
        nodosListos = new HashSet<>();
        nodosNoListos = new HashSet<>();
        distancia = new HashMap<>();
        predecesores = new HashMap<>();
        //Le doy al mapa el peso de origen a origen
        distancia.put(origen, 0.0);
        //Y a;ado al set de nodos por procesar
        nodosNoListos.add(origen);
        //mientras haya nodos sin procesar
        while (nodosNoListos.size() > 0) {
            
            //Consigo el minimo de los nodos no procesados
            NodoGrafo nodo = getMinimo(nodosNoListos);
            //Se marca listo a;adiendo a lista de procesados 
            nodosListos.add(nodo);
            //y eliminando de lista de no procesados
            nodosNoListos.remove(nodo);
            //Encuentro distancia minima 
            encontrarDistanciaMinima(nodo);
        }
    }

    /**
     * Busca en la lista de nodos adyacentes el nodo con la distancia minima
     * @param nodo Con el cual hacer el recorrido
     */
    private void encontrarDistanciaMinima(NodoGrafo nodo) 
    {
        List<NodoGrafo> adyacentes = (List<NodoGrafo>) getAdyacentes(nodo);
        //Recorro los nodos que lo conectan
        for (NodoGrafo objetivo : adyacentes)
        {
            //Si la distancia mas corta del objetivo es mayor a la distancia de
            //mas corta del nodo + la distancia del nodo al objetivo
            //Lo inserto 
            if (getDistanciaMasCorta(objetivo) > getDistanciaMasCorta(nodo) + getDistancia(nodo, objetivo)) 
            {
                distancia.put(objetivo, getDistanciaMasCorta(nodo) + getDistancia(nodo, objetivo));
                predecesores.put(objetivo, nodo);
                nodosNoListos.add(objetivo);
            }
        }

    }

    /**
     * 
     * @param origen Nodo fuente
     * @param objetivo Nodo destino
     * @return Peso de la conexion
     */
    private double getDistancia(NodoGrafo origen, NodoGrafo objetivo)
    {
        for (Conexion edge : bordes) 
        {
            if (edge.getOrigen().equals(origen) && edge.getDestino().equals(objetivo)) 
            {
                return edge.getDistancia();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * 
     * @param nodo Con el cual buscar
     * @return Lista de nodos que estan conectados a este
     */
    private List<NodoGrafo> getAdyacentes(NodoGrafo nodo) 
    {
        List<NodoGrafo> neighbors = new ArrayList<>();
        for (Conexion borde : bordes) 
        {
            if (borde.getOrigen().equals(nodo) && !hayConexion(borde.getDestino())) 
            {
                neighbors.add(borde.getDestino());
            }
        }
        return neighbors;
    }

    /**
     * Busca en nodos no procesados el nodo mas cercano
     * @param bordes
     * @return El nodo más cercano
     */
    private NodoGrafo getMinimo(Set<NodoGrafo> bordes) 
    {
        NodoGrafo minimo = null;
        //Recorro la lista de nodos por procesar
        for (NodoGrafo borde : bordes) 
        {
            if (minimo == null) 
            {
                minimo = borde;
            } else {
                //Saco el minimo de esta lista
                if (getDistanciaMasCorta(borde) < getDistanciaMasCorta(minimo)) 
                {
                    minimo = borde;
                }
            }
        }
        return minimo;
    }

    /**
     * Revisa si hay conexion
     * @param borde Busca la ruta
     * @return true si existe, false si no
     */
    private boolean hayConexion(NodoGrafo borde) 
    {
        return nodosListos.contains(borde);
    }

    /**
     * Busca la distancia minima
     * @param destino
     * @return La distancia minima
     */
    private double getDistanciaMasCorta(NodoGrafo destino) 
    {
        //Devuelve el peso de la conexion con destino del Mapa recien creado
        Double d = distancia.get(destino);
        if (d == null) 
        {
            //Devuelve el mayor numero posible para que no se tome en cuenta
            return Integer.MAX_VALUE;
        } else 
        {
            return d;
        }
    }

    /**
     * @param objetivo Al cual se requiere llegar
     * @return Ruta más corta al destino 
     */
    public LinkedList<NodoGrafo> getRuta(NodoGrafo objetivo) 
    {
        LinkedList<NodoGrafo> ruta = new LinkedList<>();
        NodoGrafo paso = objetivo;
        // Verifico si existe conexion
        if (predecesores.get(paso) == null) 
        {
            return null;
        }
        //Inserto para comenzar a buscar la ruta con este
        ruta.add(paso);
        //Añado los caminos más cortos desde el destino al origen
        while (predecesores.get(paso) != null) 
        {
            paso = predecesores.get(paso);
            //Agrego hasta llegar al origen
            ruta.add(paso);
        }
        // Le damos la vuelta para leerlo correctamente
        Collections.reverse(ruta);
        return ruta;
    }
}
