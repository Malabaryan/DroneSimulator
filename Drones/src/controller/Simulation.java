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
    private static int simulationTime;
    private static int droneCount;
    private static int stationCount;
    private static Graph<Station>  GraphMap;
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
    
    public static Graph<Station> CreateMap(int HeightTrack, int WidthTrack, int SimulationTime, int pDroneCount, int pStationCount, int tracksByStation, int mapWidth, int mapHeight){
        Simulation.setBlockSize( WidthTrack/2 * HeightTrack/3);
        Simulation.setMsBlockSize(60);
        
        simulationTime = SimulationTime;
        droneCount = pDroneCount;
        stationCount = pStationCount;
        Station[] Stations = genStations(mapWidth,mapHeight,stationCount, SimulationTime/Simulation.getMsBlockSize() + 10);
        GraphMap = new Graph();
        
        //assign stations to graph
        for(Station station : Stations){
            GraphMap.insertNode(station);
        }
        
        /*for each Station
                set Edges*/
        MapConstructor.createRandomConnections(GraphMap, stationCount, tracksByStation);
        MapConstructor.setOptimalRoutes(GraphMap);
        return GraphMap;
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
    public static long runSimulation(AlgorithmType type){
       
        
        //start measuring time
        long iniTime = System.currentTimeMillis();
        
        
        if(type == AlgorithmType.Backtracking){
        Backtracking bc = new Backtracking();
        
        bc.solve(Simulation.getBlockSize(), GraphMap, droneCount, stationCount, simulationTime,Simulation.getMsBlockSize());
        //System.out.println("Tiempo utilizado backtracking = " + (System.currentTimeMillis() - iniTime) + "ms");
       
        }else if(type == AlgorithmType.Probabilistic){
        
            Probabilistic pb = new Probabilistic();
        pb.solve2(Simulation.getBlockSize(), GraphMap, droneCount, stationCount, simulationTime,Simulation.getMsBlockSize());
        //System.out.println("Tiempo utilizado Probabilistico = " + (System.currentTimeMillis() - iniTime) + "ms");
        
        }
        
        //print used time
        //System.out.println("Tiempo utilizado backtracking = " + (System.currentTimeMillis() - iniTime) + "ms");
        
        
        /*for(GraphNode<Station> st : GraphMap.getNodes()){
        st.getContent().printTimelineIn();
        }*/
        return (System.currentTimeMillis() - iniTime);
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
