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
public class Node {
    private ArrayList<Node> adjacencys;
    //el pesos se guarda en milisegundos
    private ArrayList<Integer> weights;
    //stores the neightbor a drone should follow when visiting this node
    //acording to the target node
    private ArrayList<Integer> shortestNeigthbor;
    //permite ejecutar dijkstra
    private boolean visited;
    private int value;
    
    //ubicacion en el mapa, sirve para la interfaz
    //puede que no sea necesario
    private float x;
    private float y;
    
    public Node(float p_x, float p_y){
        x = p_x;
        y = p_y;
    }
    
    //funcion que le agrega una adjacencia al nodo
    //conversion es la cantidad de milisegundos por unidad de distancia
    public void addAdjacency(Node pNode, float pConversion){
        adjacencys.add(pNode);
        //calcula la distancia en tre ambos nodos
        float distance = (float) Math.hypot(x-pNode.getX(), y - pNode.getY());
        //calcula la cantidad de milisegundos que tardara el drone en recorrer la pista
        weights.add((int)(distance*pConversion));
    }

    public float getX() {
        return x;
    }

    public void setX(float pX) {
        this.x = pX;
    }

    public float getY() {
        return y;
    }

    public void setY(float pY) {
        this.y = pY;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean pVisited, int pValue) {
        this.visited = pVisited;
        this.value = pValue;
    
    }
}
