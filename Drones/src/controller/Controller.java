/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.TimelineController;
import java.util.Random;
import java.util.ArrayList;
import code.Timeline;
import code.*;
import helper.LexicographicalOrderedHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author DilanHO
 */
public class Controller extends Application{
    
     
 
    
    public static void main(String[] args) {
        //Simulation.runSimulation(1000, 4, 1000000, 1000000000, 30, 3, AlgorithmType.Probabilistic);
        //Simulation.runSimulation(1000, 4, 1000000, 1000000000, 30, 3, AlgorithmType.Backtracking);
        int size = 3;
        launch(args);
    }

       @Override
        public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("view/MapDisplay.fxml"));
        
        //Scene scene = new Scene(root);
        
        //stage.setScene(scene);
        //stage.show();
        

        
    }

}
