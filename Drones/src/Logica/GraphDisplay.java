/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import Logica.Station;
import generic.Node;
import helper.RandomNoiseGenerator;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 *
 * @author Carlos
 */
public class GraphDisplay {
        private static AnimationTimer timer;
        
        
        
        //Create a animation to show the animation of the graph
        public static void startDisplay(GraphicsContext gc, Canvas canvas, ArrayList<Node<Station>> nodes , int width, int height, long pSeed){
            final long startNanoTime = System.nanoTime();
         
         BufferedImage bimg = createBackground(width, height, pSeed);
         Image img = SwingFXUtils.toFXImage(bimg,null);
         
         timer = new AnimationTimer()
        {
        public void handle(long currentNanoTime)
        {
            //calcula el tamaño que debe tener el mapa en la pantalla
            double scaleFactor = Math.min((canvas.getWidth()-180)/width, canvas.getHeight()/height);
            //System.out.println(scaleFactor);
            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
             // background image clears canvas
            gc.setFill(Color.rgb(50, 74, 112,1.0));
            gc.setStroke(Color.rgb(43, 51, 42,1.0));
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(img, 0, 0,width*scaleFactor,height*scaleFactor);
            //gc.setLineWidth(2.0);
            for(generic.Node<Station> node : nodes){
                Station station = node.getContent();
                for(generic.Node<Station> neighbor : node.getPaths()){
                   gc.strokeLine((double) station.getX()*scaleFactor,(double)station.getY()*scaleFactor,
                           (double)neighbor.getContent().getX()*scaleFactor, (double)neighbor.getContent().getY()*scaleFactor);
                }
            //gc.fillRect(station.getX()-5, station.getY()-5, 10, 10);
            gc.fillArc(station.getX()*scaleFactor-5, station.getY()*scaleFactor-5, 10, 10, 0, 360, ArcType.CHORD);
            }
             
            
            
        }
        };
         timer.start();
        }
        
        //generate a random background using perling noise
        private static BufferedImage createBackground(int width, int height, long pSeed){
            RandomNoiseGenerator noise = new RandomNoiseGenerator(pSeed);
            //poner maximo al tamaño de la imagen para no afectar la simulacion ------------------------------------!!!!
            BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            //adjusts the smotoothnes of the noise
            double scale = 0.008;
            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    //generate a random smooth pixel
                    int  steps = 10;
                    double value = 256*(int)Math.floor(140*(Math.floor(noise.noise2d(i*scale, j*scale)*steps)/steps));
                    //aca se asigna los colores de la imagen generada
                    result.setRGB(i, j, 2636840 + (int)value);
                }
            }
            return result;
        }
        public static void resumeDisplay(){
            if(timer != null){
            timer.start();
            }
        }
        public static void stopDisplay(){
            if(timer != null){
            timer.stop();
            }
        }
        
        public static void resetDisplay(){
            timer = null;
            //force elimination and liberation of not used objects
            System.gc();
        }
}
