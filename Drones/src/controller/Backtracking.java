/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;
import code.GraphNode;
import helper.LexicographicalOrderedHelper;
import helper.MapHelper;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Backtracking extends SolutionAlgorithm{

    public Backtracking() {
    }
    
    
    

    @Override
    public void solve(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount, int simulationTime, int blockSize) {
       
        //System.out.println("controller.Backtracking.solve()");
        // index to headIndex and currentIndex
       int headIndex = 0;
       int currentIndex = 0 ;
       //size of the minimun size  in timeline
       int bloque = blockSize;
       int total = 0;
       //currentIndex ms of simulation
       int timestamp = 0;
       
       ArrayList<Integer> pila = new ArrayList<>();
       ArrayList<Integer> timePila = new ArrayList<>();
       ArrayList<Integer> headPila = new ArrayList<>();
       GraphNode<Station> actual, target, previous;
       Random random = new Random(System.currentTimeMillis());
       ArrayList<GraphNode<Station>> path;
       
       while(total < DronesCount){
           //System.err.println(total);
           actual = GraphMap.getNode(currentIndex);
           int index = random.nextInt(stationCount);
           //solucion temporal
           if(index == currentIndex) index = (currentIndex + 1) %stationCount;
           target = GraphMap.getNode(index);
           path = actual.getContent().getOptimalRoutes().get(target);
           
           //inserts currentIndex element on the pila
           pila.add(currentIndex);
           //headPila.add(currentIndex);
           
           //we traverse the path
           previous = actual;
           actual.getContent().reserveIn(timestamp);
           int acumulatedTime = 0;
           for(GraphNode<Station> node : path){
               acumulatedTime += MapHelper.travelTime(previous, node);
               
               //try to reserve space
               if(node.getContent().reserveIn(timestamp + acumulatedTime) && timestamp + acumulatedTime < simulationTime){
                   pila.add(node.getContent().getId());
                   timePila.add(acumulatedTime);
               }else{
                   //there is no avaiable space undo last trip
                   //System.out.println("Rejected: " + actual.getContent().getId() + ", " + target.getContent().getId() );
                   actual.getContent().freeIn(timestamp);
                   while(pila.get(pila.size()-1) != actual.getContent().getId()){
                       //get last element of pila
                       GraphNode<Station> st = GraphMap.getNode( pila.remove(pila.size()-1));
                       //get last time of timePila
                       acumulatedTime = timePila.get(timePila.size() -1);
                       
                       st.getContent().freeIn(timestamp + acumulatedTime);
                   }
               }
               
               if(node == target){
                   total += dronesByTrack;
                   //System.out.println("Accepted: " + actual.getContent().getId() + ", " + target.getContent().getId() );
                   break;
               }
               previous = node;
           }
           
           //free memory from pila
           pila.clear();
           timePila.clear();
           
           //stop condition
           if(total >= DronesCount) {
               
               break;
            }
           if(timestamp > simulationTime){
               System.out.println("Could not acomodate all drones :(");
               break;
           }
           
           currentIndex = (currentIndex+1)%stationCount;
           //if we traversed throught all nodes go to next block
           if(currentIndex == headIndex) timestamp += bloque;
           
       }
       
       
    }
       
}
