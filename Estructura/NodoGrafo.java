package Estructura;
//Bibliotecas a usar
import java.awt.Image;

/**
 *
 * @author live y edgerik
 */
public class NodoGrafo 
{
    //Variables globales
    private Lugar lugar;
    final private String id;
    final private String name;
    private Image thumbnail;
    private int posX;
    private int posY;
    private boolean isProcesado;

    /**
     * Constructor, Nodo con la imagen del lugar
     * @param id para el nodo en el hash table
     * @param lugar info del lugar 
     */
    public NodoGrafo(String id,Lugar lugar) {
        this.id = id;
        this.lugar = lugar;
        this.name = lugar.getName();
        this.thumbnail = lugar.getFoto_lugar();
        this.posX = 0;
        this.posY = 0;
        this.isProcesado = false;
    }

    
    public boolean isIsProcesado() {
        return isProcesado;
    }

    public void procesar(){
        isProcesado = true;
    }
    public void limpiar(){
        isProcesado = false;
    }
    
    public void setIsProcesado(boolean isProcesado) {
        this.isProcesado = isProcesado;
    }

    /**
     * Constructor
     * @param id para el nodo en el hash table
     * @param name info del lugar 
     */
    public NodoGrafo(String id, String name) {
        this.id = id;
        this.name = name;
        this.thumbnail = null;
        this.posX = 0;
        this.posY = 0;
    }
    //Gets and sets
    public Image getThumbnail() {
        return thumbnail;
    }
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }
    public Lugar getLugar() {
        return lugar;
    }
    public String getId() {
        return id;
    }
    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }
    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }
    public String getName() {
        return name;
    }
    /**
     * Crea codigo hash para manejo de nodos
     * @return La posicion a insertar en la tabla
     */
    @Override
    public int hashCode() 
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * Para comparar con otros nodos
     * @param obj con el cual comparar
     * @return true si es igual, false si no
     */
    @Override
    public boolean equals(Object obj) 
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodoGrafo other = (NodoGrafo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Nombre: "+name+"\tID: "+id;
    }
}