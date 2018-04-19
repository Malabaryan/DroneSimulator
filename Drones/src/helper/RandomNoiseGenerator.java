/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.Random;

/**
 *
 * @author Carlos
 */

//based on the code from book of shaders
//https://thebookofshaders.com/11/
public class RandomNoiseGenerator {
    

    private double seed2d;

    public RandomNoiseGenerator(long pSeed) {
        Random random = new Random(pSeed);
        seed2d = 30000.7275 + random.nextDouble()*30000.15784;
    }
    
    
    //generates a random floating point value interpolating the 
    //value of four random numbers surrounding it
    //this way a continues smooth random function is generated
    public double noise2d(double pX, double pY){
        //get the fractional part of the cordinate
        //this will be used to interpolate betwen the four values
        double fractX = pX - Math.floor(pX);
        double fractY = pY - Math.floor(pY);
        
        //integer part
        double ix = Math.floor(pX);
        double iy = Math.floor(pY);
        
        //get the random numbers close to the cordinate
        double a = random(ix, iy, seed2d);
        double b = random(ix + 1, iy, seed2d);
        double c = random(ix, iy + 1, seed2d);
        double d = random(ix + 1, iy + 1, seed2d);
        
        // Cubic Hermine Curve
        double ux = fractX*fractX*(3-2*fractX);
        double uy = fractY*fractY*(3-2*fractY);
        
        //interpolate and return values
        return mix(a,b,ux) + (c-a)*uy*(1.0-ux)+(d-b)*ux*uy;
    }
    
    private static double random(double x, double y, double seed){
        //any random number will do for the second matrix
        double preResult = Math.sin(dot(x,y,15.7378,79.715) * seed);
        return preResult - Math.floor(preResult);
    }
    
    private static double dot(int x, int y, int a, int b){
        return x*a + y*b;
    }
    
    private static double mix(double pX, double pY, double pA){
        return pX*(1-pA)+pY*pA;
    }
    
    private static double dot(double x, double y, double a, double b){
        return x*a + y*b;
    }
}
