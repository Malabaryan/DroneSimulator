/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;
import code.GraphNode;
import helper.MapHelper;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Backtracking extends SolutionAlgorithm{
    
    
    
    
    public static void backtracking(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount,int simulationTime){
       
       
    }

    @Override
    void solve(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount, int simulationTime) {
       // index to headIndex and currentIndex
       int headIndex = 0;
       int currentIndex = 0 ;
       //size of the minimun size  in timeline
       int bloque = 90;
       int total = 0;
       //currentIndex ms of simulation
       int timestamp = 0;
       
       ArrayList<Integer> pila = new ArrayList<>();
       ArrayList<Integer> headPila = new ArrayList<>();
       GraphNode<Station> actual, target, previous;
       Random random = new Random(System.currentTimeMillis());
       ArrayList<GraphNode<Station>> path;
       
       while(total < DronesCount){
           actual = GraphMap.getNode(currentIndex);
           int index = random.nextInt(stationCount);
           //solucion temporal
           if(index == currentIndex) index = (currentIndex + 1) %stationCount;
           target = GraphMap.getNode(index);
           path = actual.getContent().getOptimalRoutes().get(target);
           
           //inserts currentIndex element on the pila
           pila.add(currentIndex);
           headPila.add(currentIndex);
           
           //we traverse the path
           previous = actual;
           for(GraphNode<Station> node : path){
               int Time = MapHelper.travelTime(previous, node);
               //try to reserve space
               if(node.getContent().reserveIn(timestamp + Time)){
                   pila.add(node.getContent().getId());
               }else{
                   //there is no avaiable space
                   
               }
               
               
               previous = node;
           }
           
           
           currentIndex = (currentIndex+1)%stationCount;
           //if we traversed throught all nodes go to next block
           if(currentIndex == headIndex) timestamp += bloque;
           
       }
       
       
    }
    
    

    
 

    
}
