/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.BitSet;

/**
 *
 * @author DilanHO
 */
public class Timeline {

    private BitSet Line;
    /*Timeline*/
    private int maxSpace;
    /*Lenght of array Line*/
    private int maxByTime;
    /*Max int by unit of array Line*/
    private TimelineController Controller;
    
    //minimun amount of time that can be reserved
    private int blockSize = 90;

    /*Manage Time*/
    public Timeline(int pMaxSpace, TimelineController pController) {

        Line = new BitSet(pMaxSpace);
        maxSpace = pMaxSpace;
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
        
            if (Line.get(index)) {
                
                return false;
            } else {
                Line.set(maxByTime);
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
            Line.set(maxByTime,false);
                
    }




}
