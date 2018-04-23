/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.TimelineController;

/**
 *
 * @author DilanHO
 */
public class Controller {
    
    private TimelineController TimeControl;
    
    private Station[] genStations(int weightMap, int heightMap, int stationCount){
        int sizeOfMatrix = 6;
        Random rand = new Random();
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
    

    public void runSimulation(int HeightTrack, int WeightTrack, int SimulationTime, int stationCount){
        
        Station[] Stations = genStations(500,500,stationCount);
        
        /*
        - Dejar listas las estaciones
        - Correr los dijsktras
        - Correr los aleatorios para las pistas

        *Metodo de cada iuno (Bryan, Charlie, Edgerik)*
        */
    }
    
    public static void main(String[] args) {
        
    }
}
