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
public class ReportController {
    private Report[] reports;
    int realtime;
    int totalTime;
    int pointer;
    int totalFrames;
    boolean newAnimation;
    //sngleton instances of this class
    static ReportController instance;
    
    public ReportController(int totaltTime, int realTime, int nodos){
        this.totalTime = totaltTime;
        this.realtime = realTime;
        
        
        totalFrames = (int)(realTime/33.3333333 + 100);
        reports = new Report[totalFrames];
    }
    
    public static void createReportController(int totaltTime, int realTime, int nodos){
        instance = new ReportController(totaltTime,realTime,nodos);
    }
    
    /**
     * Adds a report to the timeline
     * @param nodoFuente
     * @param nodoDestino
     * @param timestamp
     * @param amout 
     */
    public synchronized void addDrones(int nodoFuente,int nodoDestino, int timestamp,int amout){
        int targetFrame = timestamp /totalTime*totalFrames;
        if(targetFrame < totalFrames){
            reports[targetFrame].addTrips(nodoFuente, nodoDestino, amout);
        }
    }
    
    public synchronized void setTotalDrones( int timestamp,int amout){
        int targetFrame = timestamp /totalTime*totalFrames;
        if(targetFrame < totalFrames){
            reports[targetFrame].setDronesEnviados(amout);
        }
    }
    
    public synchronized static ReportController getInstance(){
        return instance;
    } 
    
    public synchronized Report getReport(){
        return reports[pointer];
        
    }
    
    public synchronized void incrementFrame(){
        if(totalFrames > pointer){ 
            pointer++;
        }else{
            this.newAnimation = false;
        }
    }
    
    public synchronized boolean finished(){
        return pointer >= totalFrames;
    }
    public synchronized boolean getNewAnimation() {
        return newAnimation;
    }
    
    public synchronized void setNewAnimation(boolean newAnimation) {
        this.newAnimation = newAnimation;
    }

    public synchronized void setPointer(int pointer) {
        this.pointer = pointer;
    }
    
    
    
}
