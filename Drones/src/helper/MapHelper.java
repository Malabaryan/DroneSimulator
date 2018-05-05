/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import controller.Station;
import code.GraphNode;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class MapHelper {
    
    //returns the distance betwen two stations
    /**
     * Calculates the distance betwen two stations
     * @param pFirstLocation
     * @param pSecondLocation
     * @return 
     */
    public static double distance(Station pFirstLocation, Station pSecondLocation){
        return Math.hypot(pFirstLocation.getX() - pSecondLocation.getX(), pFirstLocation.getY() - pSecondLocation.getY());
    }
    
    /**
     * return the travel time betwem two nodes in the graph
     * @param pFirstLocation frist Node
     * @param pSecondLocation   second Node
     * @return travel time
     */
    public static int travelTime(GraphNode<Station> pFirstLocation, GraphNode<Station> pSecondLocation){
        return (int)(Math.hypot(pFirstLocation.getContent().getX() - pSecondLocation.getContent().getX(),
                               pFirstLocation.getContent().getY() - pSecondLocation.getContent().getY())*30000);
    }
    /**
     * return the travel time betwen two stations
     * @param pFirstLocation first Station
     * @param pSecondLocation second Station
     * @return travel time
     */
    public static int travelTime(Station pFirstLocation, Station pSecondLocation){
        return (int)(Math.hypot(pFirstLocation.getX() - pSecondLocation.getX(),
                               pFirstLocation.getY() - pSecondLocation.getY())*30000);
    }
    
    //for debuging
    public static void printOptimalRoutes(Map<GraphNode<Station>,ArrayList<GraphNode<Station>>> routes, ArrayList<GraphNode<Station>> nodes){
        ArrayList<GraphNode<Station>> list;
        for(GraphNode<Station> station : nodes){
            list = routes.get(station);
            for(GraphNode<Station> node : list){
                System.out.print(node.getContent().getX() + ", " + node.getContent().getY() + " >< ");
            }
            System.out.println("---");
        }
            
        }
    }

