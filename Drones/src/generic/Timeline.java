/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

/**
 *
 * @author DilanHO
 */
public class Timeline {

    private int[] Line;
    /*Timeline*/
    private int maxSpace;
    /*Lenght of array Line*/
    private int maxByTime;
    /*Max int by unit of array Line*/
    private TimelineController Controller;

    /*Manage Time*/
    public Timeline(int pMaxSpace, TimelineController pController) {

        Line = new int[maxSpace];
        maxSpace = pMaxSpace;
    }

    private boolean miliSecondIsFull(int pMiliSecond) {
        if (Line[pMiliSecond] >= maxByTime) {
            return true;
        }
        return false;
    }

    public int addElementsToMiliSecond(int pMiliSecond, int elementsCount) {
        if (pMiliSecond < getactualTime()) {
            if (elementsCount + Line[pMiliSecond] <= maxByTime) {
                Line[pMiliSecond] += elementsCount;
                return 0;
            } else {
                elementsCount+= Line[pMiliSecond];      /*this line is used by the return*/
                Line[pMiliSecond] = maxByTime;
                return elementsCount - maxByTime;
            }
        }
        return elementsCount;
    }
    
    private int getactualTime() {
        Line[Controller.getActualTime() % maxSpace] = 0;
        return Controller.getActualTime();
    }

}
