/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generic;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Node<T> {
        private ArrayList<Node<T>> paths;
        private ArrayList<Double> weights;
        private boolean visited;
        private T content;
        
        
        //conect this to other node
        //returns true if its not alredy connected to it
        public boolean connect(Node pTarget, double pWeight){
            if(paths.contains(pTarget)) return false;
            paths.add(pTarget); 
            weights.add(pWeight);
            return true;
        }

        public Node(T content) {
            this.content = content;
            paths = new ArrayList<Node<T>>();
            weights = new ArrayList<Double>();
            visited = false;
        }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Node<T>> getPaths() {
        return paths;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
        
        
        
}
