/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Code.GraphNode;
import Code.Timeline;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class Station {
    private int x;
    private int y;
    private Timeline LineIn;
    private Timeline LineOut;
    //precomputed optimal routes
    private Map<Code.GraphNode<Station>,ArrayList<Code.GraphNode<Station>>> optimalRoutes;
    
    public Station() {
        this.x = 0;
        this.y = 0;
        
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

    public Map<GraphNode<Station>, ArrayList<GraphNode<Station>>> getOptimalRoutes() {
        return optimalRoutes;
    }

    public void setOptimalRoutes(Map<GraphNode<Station>, ArrayList<GraphNode<Station>>> optimalRoutes) {
        this.optimalRoutes = optimalRoutes;
    }

    public Timeline getLineIn() {
        return LineIn;
    }

    public void setLineIn(Timeline LineIn) {
        this.LineIn = LineIn;
    }

    public Timeline getLineOut() {
        return LineOut;
    }

    public void setLineOut(Timeline LineOut) {
        this.LineOut = LineOut;
    }

    
    
    
    
}
