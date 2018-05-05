/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;
import code.GraphNode;
import controller.Station;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.util.Pair;


/**
 *
 * @author Carlos
 */
public class DivideAndConquer extends SolutionAlgorithm{

    @Override
    public void solve(
            int dronesByBlock, Graph<Station> GraphMap, 
            int DronesCount, int stationCount, 
            int simulationTime, int blockSize) {
        
        Random randomSeed = new Random();
        int trips = DronesCount/dronesByBlock;
        
        List<Pair<int[], int[]>> Routes;
        Routes = new ArrayList();
        
        ArrayList<GraphNode<Station>> TempRoute;
        for(int cantTripsLeft = 0;trips < cantTripsLeft; cantTripsLeft++){
            TempRoute = GraphMap.getNode( 
                    randomSeed.nextInt(stationCount) ).getContent().getOptimalRoutes().get(
                    GraphMap.getNode( randomSeed.nextInt(stationCount) ));
            
            Routes.add(new Pair( 
                    getWays(TempRoute,GraphMap ),
                    getWays(TempRoute,GraphMap ))
                                );
        }
        
        solveAux( Routes, GraphMap );
    }
    
    // Convert a Route on a two arrays of stationCount ints
    int[] getWays(ArrayList<GraphNode<Station>> pRoute, Graph<Station> pGraphMap ){
        
       int[] ways = new int[pGraphMap.getNodes().size()];
       GraphNode<Station> TempNode = pRoute.get(0);
       for( GraphNode<Station> Node : pRoute ){
           ways[pGraphMap.getNodes().indexOf(Node)] = (int)(distance(
                   TempNode.getContent().getX(), TempNode.getContent().getY(), 
                   Node.getContent().getX(),Node.getContent().getY())) / 120;
           TempNode = Node;
       }
       
       return ways;
       
    }
    
    // Recursive function that divide and conquer
    List<Pair<int[], int[]>> solveAux(List< Pair<int[], int[]> > pRoutes, Graph<Station> pGraphMap ){
        
        if(pRoutes.size() == 1){
            return pRoutes;
	}
        
        return compareTwoArrays( 
        solveAux( pRoutes.subList(0, pRoutes.size()/2) ,pGraphMap),
        solveAux( pRoutes.subList(pRoutes.size()/2 + 1,pRoutes.size() ),pGraphMap)        
        );
        
    }
    
    // Return pLeftArray + pRightArray
    List<Pair<int[], int[]>> compareTwoArrays(List<Pair<int[], int[]>> pLeftArray, List<Pair<int[], int[]>> pRightArray){
        
        // Saca el minimo entre todos
        // Le suma a todos los de la derecha para que queden igual que el minimo
        // for para revisar nodo por nodo cuanto mas hay que correrlo a la 
	    
	    int minimum = Integer.MAX_VALUE;
        for(int leftIndex = 0; leftIndex < pLeftArray.get(pLeftArray.size() - 1).getValue().length; leftIndex++ ){
            if(pLeftArray.get(pLeftArray.size() - 1).getValue()[leftIndex] < minimum){
                minimum = pLeftArray.get(pLeftArray.size() - 1).getValue()[leftIndex];
            }
        }
        
        pLeftArray.addAll(pRightArray);
        return pLeftArray;
    }
    
    public double distance(int x1, int y1, int x2, int y2) {

        return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));
    }
    
  
    
    
    

}
