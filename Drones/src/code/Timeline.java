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
    private int maxSpace;
    private int maxByTime;
    private TimelineController Controller;

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
        return addElementsToMiliSecond(pMiliSecond,pMiliSecond,getactualTime());
    }

    public int addElementsToMiliSecond(int pMiliSecond, int elementsCount, int actualTime) {
        Line[actualTime % maxSpace] = 0;
        if (pMiliSecond < actualTime) {
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
    
        public int removeElementsToMiliSecond(int pMiliSecond, int elementsCount) {
            if(Line[pMiliSecond] == 0){
                return 0;
            }
            else if(Line[pMiliSecond] - elementsCount <= 0){
                return 0;
            }
            else{
                Line[pMiliSecond]-=elementsCount;
                return Line[pMiliSecond];
            }
        }


    private int getactualTime() {
        Line[Controller.getActualTime() % maxSpace] = 0;
        return Controller.getActualTime();
    }
    
    public int getFreeSpace(int miliSecond){
       return 0; 
    }

}
