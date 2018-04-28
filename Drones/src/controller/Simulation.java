/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import code.Graph;
import code.GraphNode;
import code.Timeline;
import code.TimelineController;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Simulation {
    
    private static TimelineController TimeControl;
    private static int blockSize;//in drones
    private static int msBlockSize;//in ms
    private static Station[] genStations(int weightMap, int heightMap, int stationCount, int pTimelineSize){
        int sizeOfMatrix = 6;
        Random rand = new Random(System.currentTimeMillis());
        Station[] Stations = new Station[stationCount];
        int xMatrix,yMatrix ;
        for(int i = 0; i < stationCount; i++){
            xMatrix = rand.nextInt(sizeOfMatrix);   /* 0 =< rand < sizeOfMatrix*/
            yMatrix = rand.nextInt(sizeOfMatrix);   /* 0 =< rand < sizeOfMatrix*/

            Stations[i] = new Station(
                    rand.nextInt(xMatrix +1 * weightMap/sizeOfMatrix) + xMatrix * weightMap/sizeOfMatrix,
                    rand.nextInt(yMatrix +1 * heightMap/sizeOfMatrix) + yMatrix * heightMap/sizeOfMatrix, i,pTimelineSize );
            //tStation.setLineIn(new Timeline(5,TimeControl));
        }
        return Stations;
    }
    /**
     * Solves the simulation
     * @param HeightTrack heigth of every track in meters
     * @param WidthTrack widht of track in meters
     * @param SimulationTime time in miliseconds
     * @param droneCount drones that need to fly
     * @param stationCount stations that need to be created
     * @param tracksByStation minimun conections per station
     */
    public static void runSimulation(int HeightTrack, int WidthTrack, int SimulationTime, int droneCount, int stationCount, int tracksByStation){
        Simulation.setBlockSize( WidthTrack/2 * HeightTrack/3);
        Simulation.setMsBlockSize(60);
        
        
        Station[] Stations = genStations(6,6,stationCount, SimulationTime/Simulation.getMsBlockSize() +10);
        Graph<Station> GraphMap = new Graph();
        
        //assign stations to graph
        for(Station station : Stations){
            GraphMap.insertNode(station);
        }
        
        /*for each Station
                set Edges*/
        MapConstructor.createRandomConnections(GraphMap, stationCount, 2);
        MapConstructor.setOptimalRoutes(GraphMap);
        
        Backtracking bc = new Backtracking();
        long iniTime = System.currentTimeMillis();
        bc.solve(Simulation.getBlockSize(), GraphMap, droneCount, stationCount, droneCount,Simulation.getMsBlockSize());
        //System.out.println("Solved!");
        
        System.out.println("Tiempo utilizado = " + (System.currentTimeMillis() - iniTime) + "ms");

        /*for(GraphNode<Station> st : GraphMap.getNodes()){
        st.getContent().printTimelineIn();
        }*/
    }

    public static int getBlockSize() {
        return blockSize;
    }

    public static void setBlockSize(int blockSize) {
        Simulation.blockSize = blockSize;
    }

    public static int getMsBlockSize() {
        return msBlockSize;
    }

    public static void setMsBlockSize(int msBlockSize) {
        Simulation.msBlockSize = msBlockSize;
    }
    
    
    
}
