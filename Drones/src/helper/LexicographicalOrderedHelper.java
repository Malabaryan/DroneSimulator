/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Carlos
 */
public class LexicographicalOrderedHelper{
    int comb;
    int size;
    boolean finished;
    private int[] data;
    public LexicographicalOrderedHelper(int size) {
        this.comb = 0;
        this.size = size;
        data = new int[size];
        finished = false;
        for(int i = 0; i < size; i++){
            data[i] = i;
        }
        
    }
    /**
     * Returns the next permutation in lexicographical order
     * @return int array
     */
    public int[] getNext(){
        int[]  res = data;
        data = nexCombination(data, size);
        comb++;
        return res;
    }
    
    public void Next(){
        int[]  res = data;
        data = nexCombination(data, size);
        comb++;
    }
    
    public int[] nexCombination(int[] pArray,int pSize){
        int lastRightmost = 0;
        int i;
        for(i = pSize -2; i >= 0; --i){
            if(pArray[i] < pArray[i+1]){
                lastRightmost = i;
                //System.out.println("elements: " + pArray[i] + "<" + pArray[i+1]);
                break;
            }
        }
        if(i == -1){
            finished = true;
            return pArray;
        }
        
        int ceilIndex = 0;
        int ceilIndexValue = Integer.MAX_VALUE;
        for( i = size-1; i > lastRightmost; i--){
            if(pArray[i] > pArray[lastRightmost] && pArray[i] < ceilIndexValue){
                ceilIndexValue = pArray[i];
                ceilIndex = i;
            }
        }
        swap(pArray,ceilIndex,lastRightmost);
        
        //System.out.println("rightmost" + lastRightmost);
        reverse(pArray,lastRightmost+1,pSize-1);
        return pArray;
        
        
    }
    
    //swaps two elements in an array
    private static void swap(int[] pArray, int pIndex1, int pIndex2){
        int tmp = pArray[pIndex1];
        pArray[pIndex1] = pArray[pIndex2];
        pArray[pIndex2] = tmp;
        
    }
    
    //reverses an array
    private static void reverse(int[] pArray, int l, int h)
    {
    while (l < h)
    {
       swap(pArray,l,h);
       l++;
       h--;
   }
    
    
}

    public boolean isFinished() {
        return finished;
    }
    /**
     * 
     * @return the amount of combinatins that have been used
     */
    public int getComb() {
        return comb;
    }

    public int[] getActualPermutation() {
        return data;
    }
    
    
    
}
