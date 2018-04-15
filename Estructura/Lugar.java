package Estructura;
//Bibliotecas a usar
import java.awt.Image;
/**
 *
 * @author live y edgerik
 */
public class Lugar 
{
    //Variables globales
    private String name;
    private double latitud;
    private double longitud;
    private double puntaje;
    private String[] tipos;
    private Image foto_lugar;

    /**
     * Constructor, Valor para los nodos del grafo
     * @param name Nombre de lugar
     * @param latitud posicion y en mapa
     * @param longitud posicion x en mapa
     * @param puntaje del lugar para calcular rating de jugadores
     * @param tipos lista de tipos de lugar en los que se clasifica este
     * @param foto_lugar thumbnail de la imagen (icono)
     */
    public Lugar(String name, double latitud, double longitud, double puntaje, String[] tipos, Image foto_lugar) 
    {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.puntaje = puntaje;
        this.tipos = tipos;
        this.foto_lugar = foto_lugar;
    }

    /**
     * Constructor, Valor para los nodos del grafo
     * @param name Nombre de lugar
     * @param latitud posicion y en mapa
     * @param longitud posicion x en mapa
     * @param puntaje del lugar para calcular rating de jugadores
     * @param tipos lista de tipos de lugar en los que se clasifica este
     */
    public Lugar(String name, double latitud, double longitud, double puntaje, String[] tipos) {
        this.name = name;
        this.latitud = latitud;
        this.longitud = longitud;
        this.puntaje = puntaje;
        this.tipos = tipos;
    }
    //Gets and Sets
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLatitud() {
        return latitud;
    }
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }
    public double getLongitud() {
        return longitud;
    }
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
    public double getPuntaje() {
        return puntaje;
    }
    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
    public String[] getTipos() {
        return tipos;
    }
    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }
    public Image getFoto_lugar() {
        return foto_lugar;
    }
    public void setFoto_lugar(Image foto_lugar) {
        this.foto_lugar = foto_lugar;
    }
    @Override
    public String toString()
    {
        return "Nombre: "+ name + "  Lat: " + Double.toString(latitud)+ "  Lng: " + Double.toString(longitud) +  "  Rate: "+  Double.toString(puntaje) + "  Tipo: " + tipos[0];
    }
}
