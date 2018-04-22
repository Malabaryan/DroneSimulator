/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import Logica.Station;
import generic.Node;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class MapHelper {
    
    //returns the distance betwen two stations
    public static double distance(Station pFirstLocation, Station pSecondLocation){
        return Math.hypot(pFirstLocation.getX() - pSecondLocation.getX(), pFirstLocation.getY() - pSecondLocation.getY());
    }
    
    
    //for debuging
    public static void printOptimalRoutes(Map<Node<Station>,ArrayList<Node<Station>>> routes, ArrayList<Node<Station>> nodes){
        ArrayList<Node<Station>> list;
        for(Node<Station> station : nodes){
            list = routes.get(station);
            for(Node<Station> node : list){
                System.out.print(node.getContent().getX() + ", " + node.getContent().getY() + " >< ");
            }
            System.out.println("---");
        }
            
        }
    }

