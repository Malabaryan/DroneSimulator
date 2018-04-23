/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class GraphNode<T> {
        private ArrayList<GraphNode<T>> paths;
        private ArrayList<Double> weights;
        private boolean visited;
        private T content;
        
        
        //conect this to other GraphNode
        //returns true if its not alredy connected to it
        public boolean connect(GraphNode pTarget, double pWeight){
            if(paths.contains(pTarget)) return false;
            paths.add(pTarget); 
            weights.add(pWeight);
            return true;
        }

        public GraphNode(T content) {
            this.content = content;
            paths = new ArrayList<GraphNode<T>>();
            weights = new ArrayList<Double>();
            visited = false;
        }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<GraphNode<T>> getPaths() {
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
