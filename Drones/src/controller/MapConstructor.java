/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Station;
import code.Graph;
import code.GraphNode;
import helper.MapHelper;
import helper.RandomDistributedNumber;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Carlos
 */
//this class is in charge of generating maps
//of given characteristics
public class MapConstructor {
    //size in pixels
    private int width;
    private int height;
    
    //conversion rate
    double KmPerPixel;
    
    private int pNodes;
    private Graph<Station> graph;
    
    public MapConstructor(int width, int height,double pKmPerPixel, int pNodes, int pTrips) {
        this.width = width;
        this.height = height;
        this.pNodes = pNodes;
        //a random number generator is created
        Random random = new Random(System.currentTimeMillis());
        this.graph = new Graph<Station>();
        
        //the pNodes are created for the graph
        //maybe random needs to be changed so the pNodes are distributed better ------------------------------------------------!!!!!
        for(int i = 0; i < pNodes; i++){
            //genera a los nodos a cierta distancia de la orilla
            graph.insertNode(new Station(10+random.nextInt(width-20),10+random.nextInt(height-20),i));
        }
        
        ArrayList<GraphNode<Station>> graphNodes = graph.getNodes();
        //generate the first connection for every node
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < pNodes; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        //the list is shufled so every node is conected to other node 
        //without repeating, not exatctly what the Professor wants
        for(int i =0; i < pNodes/2; i++){
            GraphNode<Station> first = graph.getNode(i);
            GraphNode<Station> second = graph.getNode(i+1);
            //the two pNodes are connected with the corresponding weight
            graph.addEdge(first, second, MapHelper.distance(first.getContent(), second.getContent())*pKmPerPixel);
        }
        //if the is an odd number of pNodes, the las random node is connected with the first random node
        if(pNodes%2 != 0){
            GraphNode<Station> first = graph.getNode(pNodes-1);
            GraphNode<Station> second = graph.getNode(0);
            //the two pNodes are connected with the corresponding weight
            graph.addEdge(first, second, MapHelper.distance(first.getContent(), second.getContent())*pKmPerPixel);
        }
        //second random connection, to the closest node
        //this can be run without the first part, and stil generate two connections for every node
        int connections = 2;
        for(GraphNode<Station> node : graphNodes){
            ArrayList<GraphNode<Station>> otherNodes = getClosest(graph, node);
            int index = 0;
            //can be changed to force a connection for every node
            //so some may have more than two connections
            //just change while to for and use connections as goal
            while(node.getPaths().size() < connections){
                //there are no more pNodes to connect with
                //System.out.println("...");
                if(index >= otherNodes.size()) break;
                GraphNode<Station> other = otherNodes.get(index);
                graph.addEdge(node, other, MapHelper.distance(node.getContent(), other.getContent()));
                index++;
            }
        }
        
        //se guarda la lista de caminos optimos dentro de cada nodo
        for(GraphNode<Station> node : graphNodes){
            //ejecuta dijkstra para el nodo
            node.getContent().setOptimalRoutes(graph.dijkstraList(node));
        }
        //amount of drones that have to go in any rout
        ArrayList<Integer> randomTrips = RandomDistributedNumber.distribute(pNodes*pNodes - pNodes,pTrips,System.currentTimeMillis());
        
        //debug
        /*System.out.println(pTrips + " " + pNodes);
        for(int i = 0; i < pNodes*pNodes; i++){
            System.out.println(randomTrips.get(i));
        }
        System.out.println("-----");
        */
        for(int i = 0; i < pNodes; i++){
            for(int j = 0; j < pNodes; j++){
                if(i != j){
                    //using remove we are actuali taking of the first element of the array
                    graphNodes.get(i).getContent().setTrips(graphNodes.get(j), randomTrips.remove(0));
                //System.out.println(randomTrips.remove(0));
                }
             }
        }
        
        //debug imprime los caminos de un nodo para ver el resultado de dijkstra
        //MapHelper.printOptimalRoutes(graph.dijkstraList(graphNodes.get(1)), graph.getNodes());
    }
    /*Bitset Timeline
travelTime en mapHelper
un poco de javadoc*/
    
    /**
     * Crea connecines aleatorias entre todos los nodos del grafo segun sea necesario
     * @param graph
     * @param pNodes
     * @param pTrips 
     */
    public static void createRandomConnections(Graph graph, int pNodes, int pConnectionsPerNode){
    ArrayList<GraphNode<Station>> graphNodes = graph.getNodes();
        //generate the first connection for every node
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < pNodes; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        //the list is shufled so every node is conected to other node 
        //without repeating, not exatctly what the Professor wants
        for(int i =0; i < pNodes/2; i++){
            GraphNode<Station> first = graph.getNode(i);
            GraphNode<Station> second = graph.getNode(i+1);
            //the two pNodes are connected with the corresponding weight
            graph.addEdge(first, second, MapHelper.distance(first.getContent(), second.getContent()));
        }
        //if the is an odd number of pNodes, the las random node is connected with the first random node
        if(pNodes%2 != 0){
            GraphNode<Station> first = graph.getNode(pNodes-1);
            GraphNode<Station> second = graph.getNode(0);
            //the two pNodes are connected with the corresponding weight
            graph.addEdge(first, second, MapHelper.distance(first.getContent(), second.getContent()));
        }
        //second random connection, to the closest node
        //this can be run without the first part, and stil generate two connections for every node
        for(GraphNode<Station> node : graphNodes){
            ArrayList<GraphNode<Station>> otherNodes = getClosest(graph, node);
            int index = 0;
            //can be changed to force a connection for every node
            //so some may have more than two connections
            //just change while to for and use connections as goal
            while(node.getPaths().size() < pConnectionsPerNode){
                //there are no more pNodes to connect with
                //System.out.println("...");
                if(index >= otherNodes.size()) break;
                GraphNode<Station> other = otherNodes.get(index);
                graph.addEdge(node, other, MapHelper.distance(node.getContent(), other.getContent()));
                index++;
            }
        }
        
        //se guarda la lista de caminos optimos dentro de cada nodo
        for(GraphNode<Station> node : graphNodes){
            //ejecuta dijkstra para el nodo
            node.getContent().setOptimalRoutes(graph.dijkstraList(node));
        }
    
    }
    
    
    
    
    
    
    
    
    //get the closest node even if it is not connected ----------------------------------------------------------------------------
    private static ArrayList<GraphNode<Station>> getClosest(Graph pGraph, GraphNode<Station> pNode){
        ArrayList<Pair> result = new ArrayList<>();
        ArrayList<GraphNode<Station>> usefullResult = new ArrayList<>();
        double minDist = Double.MAX_VALUE;
        ArrayList<GraphNode<Station>> pNodes = pGraph.getNodes();
        //May be optimized
        //Not very important on simulation time, affects map creation time
        for(GraphNode<Station> comparedNode : pNodes){
  
            if(comparedNode != pNode){
                //add pNodes to the arrayList
                result.add(new Pair(MapHelper.distance(comparedNode.getContent(), pNode.getContent()),comparedNode));
            }
        }
        Collections.sort(result);
        for(Pair pair : result){
            usefullResult.add(pair.node);
        }
        
        return usefullResult;
    }

    //helper class to order the array of closest pNodes
    private static class Pair implements Comparable<Pair>
    {
    public Double value;
    public GraphNode<Station> node;
    public Pair(double pValue,GraphNode<Station> pNode){
            node = pNode;
            value = pValue;
    }
    public int compareTo(Pair that) {
        return this.value.compareTo(that.value);
    }
    }
    
    //returns an ArrayList with all the stations on the map
    public ArrayList<Station> getStations(){
        ArrayList<Station> stations = new ArrayList<Station>();
        ArrayList<GraphNode<Station>> nodes = graph.getNodes();
        for(GraphNode<Station> node: nodes){
            stations.add(node.getContent());
        }
        
        return stations;
    }
    
    public ArrayList<GraphNode<Station>> getNodes(){

         return graph.getNodes();
      
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
