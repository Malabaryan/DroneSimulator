/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Code;

/**
 *
 * @author DilanHO
 */
public class Timeline {
    
    
    private int [] Line;                        /*Timeline*/
    private int maxSpace;                       /*Lenght of array Line*/
    private int maxByTime;                      /*Max int by unit of array Line*/
    private TimelineController Controller;      /*Manage Time*/

    public Timeline(int pMaxSpace, TimelineController pController) {
        
        Line = new int[maxSpace * 2];
        maxSpace = pMaxSpace;
    }
    
    private boolean miliSecondIsFull(int pMiliSecond){
        if(Line[pMiliSecond] >= maxByTime){
            return true;
        }
        return false;
    }
    
    public boolean addElementToMiliSecond(int pMiliSecond){
        if(pMiliSecond < getactualTime())
        if(!miliSecondIsFull(pMiliSecond)){
            Line[pMiliSecond]++;
            return true;
        }
            return false;
    }
    
    private int getactualTime(){
        Line[Controller.getActualTime()%maxSpace] = 0;
        return Controller.getActualTime();
    }
    
    
}
