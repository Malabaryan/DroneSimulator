/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import code.NotifyingThread;
import controller.AlgorithmType;
import controller.Simulation;

/**
 *
 * @author Carlos
 */
public class SimulationThread extends NotifyingThread{
    private AlgorithmType type = AlgorithmType.Backtracking;
    public long doRun(){
        return Simulation.runSimulation(type);
    }

    public AlgorithmType getType() {
        return type;
    }

    public void setType(AlgorithmType type) {
        this.type = type;
    }
    
    
    
    
}
