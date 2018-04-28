/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.TimelineController;
import java.util.Random;
import java.util.ArrayList;
import code.Timeline;
import code.*;


/**
 *
 * @author DilanHO
 */
public class Controller {
    
    
    

    
    
   
    
    public static void main(String[] args) {
        Simulation.runSimulation(1000, 4, 10000000, 50000000, 30, 3, AlgorithmType.Probabilistic);
        Simulation.runSimulation(1000, 4, 10000000, 50000000, 30, 3, AlgorithmType.Backtracking);
    }
}
