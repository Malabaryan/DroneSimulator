/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Graph {
    private ArrayList<Node> nodes;
    private final float scale = (float) 0.0083333333;
    
    public Graph(int total){
        nodes = new ArrayList<>(total);
    }
    
    //conecta dos nodos dando su indice en la lista de nodos del grafo
    public boolean connectNodes(int pFirstNode, int pSecondNode){
        try{
        //se buscan los dos nodos a conectar
        Node firstElement = nodes.get(pFirstNode);
        Node secondElement = nodes.get(pSecondNode);
        //se conectan ambos nodos
        firstElement.addAdjacency(secondElement, scale);
        secondElement.addAdjacency(firstElement, scale);
        }catch(IndexOutOfBoundsException e){
            //retorna que la coneccion fallo
            return false;
        }
        //retorna que la coneccion se ha realizado correctamente
        return true;
    }
   
   //conecta dos nodos recibiendo los objetos de tipo nodo
   public boolean connectNodes(Node pFirstNode, Node pSecondNode){
       pFirstNode.addAdjacency(pSecondNode, scale);
       pSecondNode.addAdjacency(pFirstNode, scale);
       return true;
   }
}
