/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
//generates a list containing a value of a total number distributed
//into n numbers
public class RandomDistributedNumber {
    public static ArrayList<Float> distribute(int pTotal){
        //estor n number with the result porcentage for each one
        
        ArrayList<Float> result = new ArrayList<>();
        //random number generator
        Random random = new Random(System.currentTimeMillis());
        
        float total = 0;
        //create n random numbers
        for(int i = 0; i < pTotal; i++){
            result.add(random.nextFloat());
            total += result.get(i);
        }
        
        //normalize n random numbers so they sum a total of 1
        for(int i = 0; i < pTotal; i++){
            result.set(i, result.get(i)/total);
        }
        
        return result;
        
    }
    
    public static ArrayList<Float> distribute(int pTotal, long seed){
        //estor n number with the result porcentage for each one
        
        ArrayList<Float> result = new ArrayList<>();
        //random number generator
        Random random = new Random(seed);
        
        float total = 0;
        //create n random numbers
        for(int i = 0; i < pTotal; i++){
            result.add(random.nextFloat());
            total += result.get(i);
        }
        
        //normalize n random numbers so they sum a total of 1
        for(int i = 0; i < pTotal; i++){
            result.set(i, result.get(i)/total);
        }
        
        return result;
        
    }
    
    public static ArrayList<Integer> distribute(int pTotal, int sum, long seed){
        //estor n number with the result porcentage for each one
        
        ArrayList<Float> dist = new ArrayList<>();
        //random number generator
        Random random = new Random(seed);
        
        float total = 0;
        //create n random numbers
        for(int i = 0; i < pTotal; i++){
            dist.add(random.nextFloat());
            total += dist.get(i);
        }
        ArrayList<Integer> result = new ArrayList<>();
        //normalize n random numbers so they sum a total of 1
        for(int i = 0; i < pTotal; i++){
            result.add((int)(Math.round(dist.get(i)/total*sum)));
        }
        
        return result;
        
    }
    
    
    
}
