/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;

/**
 *
 * @author Carlos
 */
public abstract class SolutionAlgorithm {
    
    
    abstract void solve(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount,int simulationTime);
}
