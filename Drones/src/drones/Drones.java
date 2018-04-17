/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drones;

import Logica.MapConstructor;
import Logica.Station;
import generic.Grafo;
import generic.Node;
import helper.MapHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Carlos
 */
public class Drones extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        //grafo de prueba
        /*Grafo<Station> graph = new Grafo<Station>();
        graph.insertNode(new Station(10,11));
        graph.insertNode(new Station(5,7));
        graph.insertNode(new Station(15,6));
        graph.insertNode(new Station(8,3));
        graph.insertNode(new Station(13,2));
        Node<Station> node1 = graph.getNode(0);
        Node<Station> node2 = graph.getNode(1);
        Node<Station> node3 = graph.getNode(2);
        Node<Station> node4 = graph.getNode(3);
        Node<Station> node5 = graph.getNode(4);
        graph.connecNodes(node1, node2, MapHelper.distance(node1.getContent(),node2.getContent()));
        graph.connecNodes(node2, node3, MapHelper.distance(node2.getContent(),node3.getContent()));
        graph.connecNodes(node4, node1, MapHelper.distance(node1.getContent(),node4.getContent()));
        graph.connecNodes(node3, node5, MapHelper.distance(node3.getContent(),node5.getContent()));
        node1.getContent().setOptimalRoutes(graph.dijkstraList(node1));
        MapHelper.printOptimalRoutes(graph.dijkstraList(node1), graph.getNodes());*/
        MapConstructor map = new MapConstructor(500, 500, 1.0, 30);
        //free memory from unecesary structures created durign dijkstra
        System.gc();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
