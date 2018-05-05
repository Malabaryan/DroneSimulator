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
    
    public Report(int pInitialTimestamp, int pEndTimestamp){
        initialtimestamp = pInitialTimestamp;
        endtimestamp = pEndTimestamp;
    }
    
    public void addTrips(){
        
    }
}
