/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public class Graph<T> {

    private ArrayList<Node<T>> nodes;
    
    
    //default Graph constructor
    //maybe we should use a singleton Graph
    public Graph() {
        nodes = new ArrayList<Node<T>>(30);
    }
    
    
    
    
    
    //inserta un nodo en el Graph, passing a create node
    
    public void insertNode(Node<T> e){
        nodes.add(e);
    }
    //inserts an element passing only the contained element
    public void insertNode(T pElement){
        
        nodes.add(new Node<T>(pElement));
    }
    
    public Node<T> getNode(int pIndex){
        return nodes.get(pIndex);
    }
    
    public T getContent(int pIndex){
        return nodes.get(pIndex).getContent();
    }
    
     //connects two nodes, returns true if they were conected
    public boolean addEdge(int pIndexNode1, int pIndexNode2,double pWeight){
        return addEdge(nodes.get(pIndexNode1), nodes.get(pIndexNode2),pWeight);
    }
    
    //connects two nodes, returns true if they were conected
    public boolean addEdge(Node<T> pNode1, Node<T> pNode2,double pWeight){ 
        return pNode2.connect(pNode1,pWeight) && pNode1.connect(pNode2,pWeight);
    }
    
    //returns a map with the minimun distance to every node from a node-----------------------------------------------------------
    public  Map<Node<T>,Double> dijkstra(Node<T> pOrigen){
        Map<Node<T>,Double> distance = new HashMap<>();
        ArrayList<Node<T>> notReady = new ArrayList<>();
        //cost of going to a certain node
        distance.put(pOrigen,0.0);
        //nodes that need to be procesed
        notReady.add(pOrigen);
        while(notReady.size() > 0){
            //iterates over not visited nodes in the queue
                Node<T> node = notReady.get(0);
                notReady.remove(node);
                node.setVisited(true);
                ArrayList<Node<T>> neightbors = node.getPaths();
                ArrayList<Double> weights = node.getWeights();
                //iterates over neigthbors
                for(int i = 0; i < neightbors.size(); i++){
                    
                    Node<T> actual = neightbors.get(i);
                    double actualDistance = weights.get(i);
                    //System.out.println("."+distance.containsKey(actual));
                    if(!distance.containsKey(actual)){
                        //se guarda el nodo con la distancia de llegar a este nodo
                        //mas la distancia de visitar ese nodo
                        distance.put(actual, actualDistance + distance.get(node));
                    }else if(distance.get(actual) > distance.get(node) + actualDistance){
                        //if the distance is shorter than the previous distance
                        //we update it on the answer
                        distance.put(actual, distance.get(node) + actualDistance);
                    }
                    //if the node has not being visited is added to the queue
                    if(actual.isVisited() == false){
                        notReady.add(actual);
                    }
                    
                }
                //remove elements that have been procesed
                notReady.remove(node);
            
        }
        
        //debug
        for(int i = 0; i < nodes.size(); i++){
            System.out.println(i + ": " + distance.get(nodes.get(i)));
            //System.out.println(nodes.get(i).getWeights().get(0));
        }
        reset();
        return distance;
        
    }
    
    
    //returns a list of every optimal path, to every connected node--------------------------------------------------------------
    public  Map<Node<T>,ArrayList<Node<T>>> dijkstraList(Node<T> pOrigen){
        Map<Node<T>,Double> distance = new HashMap<>();
        Map<Node<T>,Node<T>> closest = new HashMap<>();
        ArrayList<Node<T>> notReady = new ArrayList<>();
        //cost of going to a certain node
        distance.put(pOrigen,0.0);
        //nodes that need to be procesed
        notReady.add(pOrigen);
        while(notReady.size() > 0){
            //iterates over not visited nodes in the queue
                Node<T> node = notReady.get(0);
                notReady.remove(node);
                node.setVisited(true);
                ArrayList<Node<T>> neightbors = node.getPaths();
                ArrayList<Double> weights = node.getWeights();
                //iterates over neigthbors
                for(int i = 0; i < neightbors.size(); i++){
                    
                    Node<T> actual = neightbors.get(i);
                    double actualDistance = weights.get(i);
                    //System.out.println("."+distance.containsKey(actual));
                    if(!distance.containsKey(actual)){
                        //se guarda el nodo con la distancia de llegar a este nodo
                        //mas la distancia de visitar ese nodo
                        distance.put(actual, actualDistance + distance.get(node));
                        closest.put(actual, node);
                    }else if(distance.get(actual) > distance.get(node) + actualDistance){
                        //if the distance is shorter than the previous distance
                        //we update it on the answer
                        distance.put(actual, distance.get(node) + actualDistance);
                        closest.put(actual, node);
                    }
                    //if the node has not being visited is added to the queue
                    if(actual.isVisited() == false){
                        notReady.add(actual);
                    }
                    
                }
                //can i remove a element of list during iteration?;
                
                notReady.remove(node);
            
        }
        Map<Node<T>,ArrayList<Node<T>>> result = new HashMap<>();
        //we build the answer to the desired format
        for(Node<T> node: nodes){
            //we ask if the result contains the node
            if(!result.containsKey(node)){
                
                Node<T> actual = node;
                ArrayList<Node<T>> actualList = new ArrayList<>();
                //build the path in reverse
                while(actual != pOrigen && actual != null){
                    actualList.add(actual);
                    actual = closest.get(actual);
                }
                //reverse the list so is in the order we need to visit
                Collections.reverse(actualList);
                //add the path to the answer
                result.put(node,actualList);
            }
            
            //System.out.println(i + ": " + distance.get(nodes.get(i)));
            //System.out.println(nodes.get(i).getWeights().get(0));
        }
        
        reset();
        return result;
        
    }
    

    
    //set all nodes to unvisited state
    public void reset(){
        for(Node<T> node : nodes){
            node.setVisited(false);
        }
        
        
        
    }

    public ArrayList<Node<T>> getNodes() {
        return nodes;
    }

    
    
    
}
