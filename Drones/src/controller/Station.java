/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.GraphNode;
import code.Timeline;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Map<code.GraphNode<Station>,ArrayList<code.GraphNode<Station>>> optimalRoutes;
    private Map<code.GraphNode<Station>,Integer> remainingTrips;
    private Map<code.GraphNode<Station>,Integer> costs;
    
    private int[] trips;
    private int id;
    
    
    public Station() {
        this.x = 0;
        this.y = 0;
        remainingTrips = new HashMap<GraphNode<Station>,Integer>();
        LineIn = new Timeline(100000);
        LineOut = new Timeline(100000);
    }

    public Station(int x, int y) {
        this.x = x;
        this.y = y;
        remainingTrips = new HashMap<GraphNode<Station>,Integer>();

    }
    
    public Station(int x, int y, int id) {
        this.x = x;
        this.y = y;
        remainingTrips = new HashMap<GraphNode<Station>,Integer>();
        this.id = id;
    }
    /**
     * Reserve space for incoming drones
     * @param timestamp milisecond
     */
    public boolean reserveIn(int timestamp){
        return LineIn.reserveTimeBlock(timestamp);
    }
    /**
     * free space for incoming drones
     * @param timestamp milisecond
     */
    public void freeIn(int timestamp){
        LineIn.retireTimeBlock(timestamp);
    }
    
    /**
     * reserve space for starting starting drones
     * @param timestamp milisecond
     */
    public boolean reserveOut(int timestamp){
        return LineOut.reserveTimeBlock(timestamp);
    }
    /**
     * reserve space for starting starting drones
     * @param timestamp milisecond
     */
    public void freeOut(int timestamp){
        LineOut.retireTimeBlock(timestamp);
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

    public void setTrips(GraphNode pTarget, int pAmount){
        remainingTrips.put(pTarget, pAmount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    
}
