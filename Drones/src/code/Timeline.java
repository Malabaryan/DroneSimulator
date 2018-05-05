/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import controller.Simulation;
import java.util.BitSet;

/**
 *
 * @author Carlos, Bryan, Edgerik
 */
public class Timeline {

    private BitSet Line;
    /*Timeline*/
    private int maxSpace;
    private int maxByTime;
    private TimelineController Controller;
    
    private int lowPointer = 0;
    
    //minimun amount of time that can be reserved
    private int blockSize = 90;

    public Timeline(int pMaxSpace, TimelineController pController) {

        Line = new BitSet(pMaxSpace);
        maxSpace = pMaxSpace;
        blockSize = Simulation.getMsBlockSize();
    }
    
    public Timeline(int pMaxSpace) {

        Line = new BitSet(pMaxSpace);
        maxSpace = pMaxSpace;
        blockSize = Simulation.getMsBlockSize();
    }

    /**
     * returns if a block is full
     * @param pMiliSecond
     * @return 
     */
    private boolean miliSecondIsFull(int pMiliSecond) {
        int index = (int)Math.floor(pMiliSecond/blockSize);
        return Line.get(index);
    }
    
    /**
     * reserves a block in the timeline  return false if its full
     * @param pMiliSecond   target milisecond to reserver
     * @return if the selection was sucesfull
     */
    public boolean reserveTimeBlock(int pMiliSecond) {
            int index = (int)Math.floor(pMiliSecond/blockSize);
            //move pointer
            if(lowPointer == index){
                //move pointer to next empty spot
                while(Line.get(lowPointer)){
                    lowPointer++;
                }
            }

            //System.out.println(index + ", " + blockSize);
            if (Line.get(index)) {
                
                return false;

            } else {
                Line.set(index,true);
                return true;
            }
    }
    
    public boolean reserveTimeBlockIgnore(int pMiliSecond) {
            int index = (int)Math.floor(pMiliSecond/blockSize);
            //move pointer
        
                while(Line.get(lowPointer)){
                    lowPointer++;
                }
            


            //System.out.println(index + ", " + blockSize);
            if (Line.get(index)) {
                
                return false;
            } else {
                Line.set(index,true);
                return true;
            }
    }
    /**
     * Frees up a space on the timeline
     * 
     * @param pMiliSecond 
     */
    public void retireTimeBlock(int pMiliSecond) {
           int index = (int)Math.floor(pMiliSecond/blockSize);
           Line.set(index,false);
           //move pointer
            if(lowPointer > index){
                lowPointer = index;
            } 
            
                
    }

    public BitSet getLine() {
        return Line;
    }
    
    public int getFreeSpace(int miliSecond){
       return 0; 
    }

    public void setLine(BitSet Line) {
        this.Line = Line;
    }

    public int getMaxSpace() {
        return maxSpace;
    }

    public int getBlockSize() {
        return blockSize;
    }
    
    /**
     * return index of next free spot
     * @return 
     */
    public int getNext(){
        return lowPointer;
    }
     public int getNext(int blockSize){
        return lowPointer*blockSize;
    }
    



}
