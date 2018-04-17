/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import generic.Grafo;
import generic.Node;
import helper.MapHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    
    private int nodes;
    private Grafo<Station> graph;
    
    public MapConstructor(int width, int height,double pKmPerPixel, int nodes) {
        this.width = width;
        this.height = height;
        this.nodes = nodes;
        //a random number generator is created
        Random random = new Random(System.currentTimeMillis());
        this.graph = new Grafo<Station>();
        
        //the nodes are created for the graph
        //maybe random needs to be changed so the nodes are distributed better ------------------------------------------------!!!!!
        for(int i = 0; i < nodes; i++){
            graph.insertNode(new Station(random.nextInt(width),random.nextInt(height)));
        }
        
        ArrayList<Node<Station>> graphNodes = graph.getNodes();
        //generate the first connection for every node
        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < nodes; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        //the list is shufled so every node is conected to other node 
        //without repeating, not exatctly what the Professor wants
        for(int i =0; i < nodes/2; i++){
            Node<Station> first = graph.getNode(i);
            Node<Station> second = graph.getNode(i+1);
            //the two nodes are connected with the corresponding weight
            graph.connecNodes(first, second, MapHelper.distance(first.getContent(), second.getContent())*pKmPerPixel);
        }
        //if the is an odd number of nodes, the las random node is connected with the first random node
        if(nodes%2 != 0){
            Node<Station> first = graph.getNode(nodes-1);
            Node<Station> second = graph.getNode(0);
            //the two nodes are connected with the corresponding weight
            graph.connecNodes(first, second, MapHelper.distance(first.getContent(), second.getContent())*pKmPerPixel);
        }
        //second random connection, to the closest node
        //this can be run without the first part, and stil generate two connections for every node
        int connections = 2;
        for(Node<Station> node : graphNodes){
            ArrayList<Node<Station>> otherNodes = getClosest(graph, node);
            int index = 0;
            //can be changed to force a connection for every node
            //so some may have more than two connections
            //just change while to for and use connections as goal
            while(node.getPaths().size() < connections){
                //there are no more nodes to connect with
                //System.out.println("...");
                if(index >= otherNodes.size()) break;
                Node<Station> other = otherNodes.get(index);
                graph.connecNodes(node, other, MapHelper.distance(node.getContent(), other.getContent()));
                index++;
            }
        }
        
        //se guarda la lista de caminos optimos dentro de cada nodo
        for(Node<Station> node : graphNodes){
            //ejecuta dijkstra para el nodo
            node.getContent().setOptimalRoutes(graph.dijkstraList(node));
        }
        //debug imprime los caminos de un nodo para ver el resultado de dijkstra
        MapHelper.printOptimalRoutes(graph.dijkstraList(graphNodes.get(1)), graph.getNodes());
    }
    
    
    
    //get the closest node even if it is not connected ----------------------------------------------------------------------------
    private ArrayList<Node<Station>> getClosest(Grafo pGraph, Node<Station> pNode){
        ArrayList<Pair> result = new ArrayList<>();
        ArrayList<Node<Station>> usefullResult = new ArrayList<>();
        double minDist = Double.MAX_VALUE;
        ArrayList<Node<Station>> nodes = pGraph.getNodes();
        //May be optimized
        //Not very important on simulation time, affects map creation time
        for(Node<Station> comparedNode : nodes){
  
            if(comparedNode != pNode){
                //add nodes to the arrayList
                result.add(new Pair(MapHelper.distance(comparedNode.getContent(), pNode.getContent()),comparedNode));
            }
        }
        Collections.sort(result);
        for(Pair pair : result){
            usefullResult.add(pair.node);
        }
        
        return usefullResult;
    }

    
    private static class Pair implements Comparable<Pair>
    {
    public Double value;
    public Node<Station> node;
    public Pair(double pValue,Node<Station> pNode){
            node = pNode;
            value = pValue;
    }
    public int compareTo(Pair that) {
        return this.value.compareTo(that.value);
    }
    }
    
    
}
