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
public class Grafo<T,W> {

    ArrayList<Node<T,W>> nodes;
    
    //inserta un nodo en el grafo
    public void insertNode(Node<T,W> e){
        nodes.add(e);
    }
    
    public void connecNodes(int pIndexNode1, int pIndexNode2,W pWeight){
        connecNodes(nodes.get(pIndexNode1), nodes.get(pIndexNode2),pWeight);
    }
    
    public void connecNodes(Node<T,W> pNode1, Node<T,W> pNode2,W pWeight){
        pNode1.connect(pNode1,pWeight);
        pNode2.connect(pNode2,pWeight);
    }
    
    
    
}
