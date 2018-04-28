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

/**
 *
 * @author Carlos
 */
public class DivideAndConquer extends SolutionAlgorithm{

    @Override
    public void solve(int dronesByBlock, Graph<Station> GraphMap, int DronesCount, int stationCount, int simulationTime, int blockSize) {
        
    int trips = DronesCount / dronesByBlock;
    
    ArrayList<GraphNode<Station>>[] Route;
    Route = new ArrayList[trips];

    for(int cantTripsLeft = 0;trips < cantTripsLeft; cantTripsLeft++){
        Route[trips] = GraphMap.getNode(22222222).getContent().getOptimalRoutes().get(
                                                    GraphMap.getNode( 222222 ));
    }
    
    int between = Route.length/2;
    ArrayList<GraphNode<Station>>[] RouteLeft = new ArrayList[between];
    ArrayList<GraphNode<Station>>[] RouteRight = new ArrayList[between];
    System.arraycopy(Route,0, RouteLeft,0,between);
    System.arraycopy(Route,0, RouteLeft,0,between);
    divideAux(RouteLeft, RouteRight, Route);
    }
    
    public void divideAux(ArrayList<GraphNode<Station>>[] Left, ArrayList<GraphNode<Station>>[] Right, ArrayList<GraphNode<Station>>[] Route){
        int between = Route.length/2;
        if(between < 2){
            
        }
        else{
            ArrayList<GraphNode<Station>>[] RouteLeft = new ArrayList[between];
            ArrayList<GraphNode<Station>>[] RouteRight = new ArrayList[between];
            System.arraycopy(Route,0, RouteLeft,0,between);
            System.arraycopy(Route,0, RouteLeft,0,between);
            divideAux(RouteLeft, RouteRight, Route);
        }
        
            
    }
}
