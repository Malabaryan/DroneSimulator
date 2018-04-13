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
public class Node<T,W> {
        ArrayList<Node> paths;
        ArrayList<W> weights;
        
        public void connect(Node pTarget, W pWeight){
            paths.add(pTarget); 
            weights.add(pWeight);
        }
}
