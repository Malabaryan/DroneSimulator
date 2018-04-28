/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;
import code.GraphNode;
import helper.MapHelper;
import helper.RandomDistributedNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Probabilistic extends SolutionAlgorithm{

    public Probabilistic() {
        
    }
    
    @Override
    public void solve(int dronesByBlock, Graph<Station> GraphMap, int DronesCount, int stationCount, int simulationTime, int blockSize) {

        ArrayList<GraphNode<Station>> neighbors;

        //inner parameters
        short minPathLength = 2;
        short maxPahtLength = (short)(stationCount/2);
        
        Random random = new Random();
        
        int total = 0;
        int randomPahtSize;
        int pathSize;
        int acumulatedTime = 0;
        
        //variables to store the nodes that are being conected
        GraphNode<Station> sourceNode = null, targetNode;
        
        boolean[] visited = new boolean[stationCount];
        while(total  < DronesCount){


            //create a new path to try
            ArrayList<Integer> possiblePaths;
            //try to make a path of size randomPathSize
            //dont worry if u cant, u are a good algorithm
            //you did your best
            targetNode = GraphMap.getNode(random.nextInt(stationCount-1));
            //get next free block and reserve
            int nextFreeTime = targetNode.getContent().getNextFreeTimeIn(blockSize);
            acumulatedTime = 0;
            targetNode.getContent().reserveInIgnore(nextFreeTime);
            pathSize = 1;
            //generate as much paths as posible
            while(acumulatedTime  + nextFreeTime <= simulationTime){
                sourceNode = targetNode;
                sourceNode.setVisited(true);
                
                neighbors = sourceNode.getPaths();
                possiblePaths = shuffledList(neighbors.size()-1);
                //iterate throught neighbors until there is no other neightbor to visit
                targetNode = neighbors.get(possiblePaths.remove(0));
                while(targetNode == sourceNode){
                    targetNode = neighbors.get(possiblePaths.remove(0));
                    if(possiblePaths.size() < 1){ 
                    break;
                }
                }
                
                
                
                
                //System.out.println(nextFreeTime + acumulatedTime + "time : " + simulationTime);
                acumulatedTime += MapHelper.travelTime(targetNode, sourceNode);
                //if it cannot conect to other neighbor it ends the path
                if(!targetNode.getContent().reserveIn(nextFreeTime + acumulatedTime) || targetNode.isVisited()){
                    GraphMap.reset();
                    //DESTROY PATH IF ITS OF SIZE 1
                    if(pathSize < 2){
                       //free the node
                       sourceNode.getContent().freeIn(nextFreeTime + acumulatedTime - MapHelper.travelTime(targetNode, sourceNode));
                    }else{
                        if(pathSize > 5)
                        System.out.println(pathSize);
                        total += blockSize;
                    }
                    //reserve departing trip
                    nextFreeTime = targetNode.getContent().getNextFreeTimeIn(blockSize);
                    targetNode.getContent().reserveIn(nextFreeTime + acumulatedTime);
                    pathSize = 0;
                    GraphMap.reset();
                    
                }
                pathSize++;
                
            }
            if(pathSize > 1){
                System.out.println(pathSize);
                total += blockSize;
            }
            
            
            //System.out.println(total);
        }   
        
        
        
        
    }
    
    public void solve2(int dronesByBlock, Graph<Station> GraphMap, int DronesCount, int stationCount, int simulationTime, int blockSize) {
        int total = 0;
        
        GraphNode<Station> sourceNode, targetNode;
        Random random = new Random();
         ArrayList<GraphNode<Station>> neighbors;
         ArrayList<Integer> possiblePaths;
        while(total < DronesCount){
            //try to make a path of size randomPathSize
            //dont worry if u cant, u are a good algorithm
            //you did your best
            targetNode = GraphMap.getNode(random.nextInt(stationCount-1));
            int actualTime = targetNode.getContent().getNextFreeTimeIn();
            //always move a step forward, so the algorithm does not gets stuck
            targetNode.getContent().reserveInIgnore(actualTime);
            int acumulatedTime = 0;
            int pathSize = 1;
            while(actualTime + acumulatedTime < simulationTime){
                sourceNode = targetNode;
                targetNode.setVisited(true);
                //sourceNode.setVisited(true);
                neighbors = sourceNode.getPaths();
                possiblePaths = shuffledList(neighbors.size()-1);
                //iterate throught neighbors until there is no other neightbor to visit
                 targetNode = neighbors.get(possiblePaths.remove(0));
                while(possiblePaths.size() > 0 && targetNode.isVisited()){
                    targetNode = neighbors.get(possiblePaths.remove(0));
                }
                
                //System.out.println(nextFreeTime + acumulatedTime + "time : " + simulationTime);
                acumulatedTime += MapHelper.travelTime(targetNode, sourceNode);
                if(targetNode.isVisited() || !targetNode.getContent().reserveIn(actualTime+ acumulatedTime)){
                    GraphMap.reset();
                    if(pathSize < 2){
                        targetNode.getContent().freeIn(acumulatedTime + acumulatedTime - MapHelper.travelTime(targetNode, sourceNode));
                        
                    }else{
                        //System.out.println(pathSize);
                        total += dronesByBlock;
                    }
                    targetNode.getContent().reserveIn(targetNode.getContent().getNextFreeTimeIn(blockSize));
                    
                    
                    pathSize = 1;
                }
                
                
                pathSize++;
                
                //System.out.println(acumulatedTime);
            }   
        }
    
    
    }
    
    
    private static  ArrayList<Integer> shuffledList(int stationCount){
    
           //generate a random permutation of the nodes;
        ArrayList<Integer> randomPath = new ArrayList<>();
        int PathIndex;
        for (int i = 0; i < stationCount; i++) {
            randomPath.add(i);
        }
        Collections.shuffle(randomPath);
       return randomPath;
    }
    
    //helping class
    /*private class pair{
        public GraphNode<Station> n1;
        public GraphNode<Station> n2;
        public int timestamp;
            
        pair(GraphNode<Station> n1, GraphNode<Station> n2, int timestamp){
            this.n1 = n1;
            this.n2 = n2;
            
        }

    }*/
    
}
