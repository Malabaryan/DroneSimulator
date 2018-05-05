/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Carlos
 */
public class Report {
    
    private int dronesEnviados;
    //momento en que se envia el drone
    private int endtimestamp;
    private int initialtimestamp;
    //segments used to draw the line
    
    //granularity of representation
    private int segments;
    
    //a representation of the drones moving;
    private int movingDrones[][];
    
    public Report(int pInitialTimestamp, int pEndTimestamp, int nodes){
        initialtimestamp = pInitialTimestamp;
        endtimestamp = pEndTimestamp;
        movingDrones = new int[nodes][nodes];
        for(int i = 0; i < nodes; i++){
            for(int j = 0 ; j < nodes; j++){
                movingDrones[i][j] = 0;
            }
        }
        dronesEnviados = 0;
        
        
    }
    
    public void addTrips(int initialNode, int finalNode, int drones){
        movingDrones[initialNode][finalNode] += drones;
    }
    
    public int getTrips(int initialNode, int finalNode, int drones){
        return movingDrones[initialNode][finalNode];
    }

    public int getDronesEnviados() {
        return dronesEnviados;
    }

    public void setDronesEnviados(int dronesEnviados) {
        this.dronesEnviados = dronesEnviados;
    }
    
    
}
