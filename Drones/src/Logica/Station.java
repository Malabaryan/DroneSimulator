/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import generic.Node;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class Station {
    private int x;
    private int y;
    //precomputed optimal routes
    private Map<generic.Node<Station>,ArrayList<generic.Node<Station>>> optimalRoutes;
    
    public Station() {
    }

    public Station(int x, int y) {
        this.x = x;
        this.y = y;
    }

    
    
    
    //getters and setters- ------------------------------------------------
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Map<Node<Station>, ArrayList<Node<Station>>> getOptimalRoutes() {
        return optimalRoutes;
    }

    public void setOptimalRoutes(Map<Node<Station>, ArrayList<Node<Station>>> optimalRoutes) {
        this.optimalRoutes = optimalRoutes;
    }
    
    
    
}
