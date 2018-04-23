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
import java.util.Map;


/**
 *
 * @author DilanHO
 */
public class Controller {
    
    private TimelineController TimeControl;
    
    private Station[] genStations(int weightMap, int heightMap, int stationCount){
        int sizeOfMatrix = 6;
        Random rand = new Random(System.currentTimeMillis());
        Station[] Stations = new Station[stationCount];
        int xMatrix,yMatrix ;
        for(Station tStation : Stations){
            xMatrix = rand.nextInt(sizeOfMatrix);   /* 0 =< rand < sizeOfMatrix*/
            yMatrix = rand.nextInt(sizeOfMatrix);   /* 0 =< rand < sizeOfMatrix*/

            tStation = new Station(
                    rand.nextInt(xMatrix +1 * weightMap/sizeOfMatrix) + xMatrix * weightMap/sizeOfMatrix,
                    rand.nextInt(yMatrix +1 * heightMap/sizeOfMatrix) + yMatrix * heightMap/sizeOfMatrix);
            tStation.setLineIn(new Timeline(5,TimeControl));
        }
        return Stations;
    }
    

    public void runSimulation(int HeightTrack, int WeightTrack, int SimulationTime, int stationCount, int tracksByStation){
        
        Station[] Stations = genStations(500,500,stationCount);
        Graph<Station> GraphMap = new Graph();
        for(Station thisStation : Stations){
            GraphMap.insertNode(thisStation);
        }
        
        /*for each Station
                set Edges*/
        
        for(GraphNode<Station> Nodes : GraphMap.getNodes()){
            Nodes.getContent().setOptimalRoutes(GraphMap.dijkstraList(Nodes));
        }
        
        
       
    }
    
    private void heuristicMethod(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount){
        
        TimeControl.run();
        int indexStation;
        Station thisStation;
        ArrayList<GraphNode<Station>> Route;
        int dronesSent;
        while (DronesCount>0){
            for(int i =0; i<GraphMap.getNodes().size() ;i++){
                //Asignar un valor porcentualidad para k drones / n estaciones


                //Asignar de forma de cola los viajes en cada nodo en forma secuencial

                //Asignar todos los viajes hasta que los drones se acaben y tomar como referencia el tiempo mas largo 
                //para dar el tiempo total de simulacion
                
            }
        }
    }
    
    private void probabilisticMethod(int dronesByTrack, Graph<Station> GraphMap, int DronesCount, int stationCount){
        
        TimeControl.run();
        Random rand = new Random(System.currentTimeMillis());
        int indexStation;
        Station thisStation;
        ArrayList<GraphNode<Station>> Route;
        int dronesSent;
        
        while(DronesCount > 0){
            
            indexStation = rand.nextInt(stationCount);
            thisStation = GraphMap.getContent(indexStation);
            indexStation = rand.nextInt(stationCount);
            Route = thisStation.getOptimalRoutes().get(GraphMap.getNode(indexStation));
            dronesSent = rand.nextInt(dronesByTrack) + 1;
            setTimes(thisStation,Route,dronesSent);
            DronesCount -= dronesSent;
            
        }
    }
    
    private int setTimes(Station pthisStation, ArrayList<GraphNode<Station>> Route, int Drones){
        
        double distance;
        Station thisStation = pthisStation;
        for(GraphNode<Station> StationOfRoute : Route){
                distance = Math.sqrt(Math.pow(StationOfRoute.getContent().getX() - thisStation.getX(),2) 
                + Math.pow(StationOfRoute.getContent().getY() - thisStation.getY(),2)) * 1000;  /*Formule to find distance between two points*/
            
                StationOfRoute.getContent().getLineIn().addElementsToMiliSecond(
                        (int)(TimeControl.getActualTime() + distance/120), Drones);
                StationOfRoute.getContent().getLineOut().addElementsToMiliSecond(
                        (int)(TimeControl.getActualTime() + distance/120), Drones);
                thisStation = StationOfRoute.getContent();
            }
        return 0;
    }
    
    public static void main(String[] args) {
        
    }
}
