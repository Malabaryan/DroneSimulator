/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.GraphDisplay;
import controller.MapConstructor;
import controller.Station;
import code.GraphNode;
import helper.ListenerHelper;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class MapDisplayController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Canvas c_canvas;
    @FXML
    private AnchorPane a_scene;
    @FXML
    //buttons
    private Button bIniciar;
    @FXML
    private Button bPausar;
    @FXML
    private Button bCancelar;
    @FXML
    private Button bCrearMapa;
    
    //textfields
    @FXML
    private TextField tfAnchoMapa;
    @FXML
    private TextField tfAltoMapa;
    @FXML
    private TextField tfAnchoPista;
    @FXML
    private TextField tfAltoPista;
    @FXML
    private TextField tfViajes;
    @FXML
    private TextField tfTiempo;
    @FXML
    private TextField tfTiempoReal;
    @FXML
    private TextField tfEstaciones;
    //label de informacion
    @FXML
    private Label lNotice;
    
    //custom properties
    private MapConstructor map;
    
    //private ArrayList<Node<Station>> nodes;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       lNotice.setFont(Font.font("Cambria", 15));
       lNotice.setWrapText(true);
       c_canvas.widthProperty().bind(a_scene.widthProperty().subtract(50));
       c_canvas.heightProperty().bind(a_scene.heightProperty().subtract(50));
        
       
       //add a listener to center the  label
        a_scene.widthProperty().addListener((obs, oldVal, newVal) -> {
    
        });
        
        
       //limit input on textfields to numbers
        ListenerHelper.convert_to_number_field(tfAltoMapa);
        ListenerHelper.convert_to_number_field(tfAnchoMapa);
        ListenerHelper.convert_to_number_field(tfEstaciones);
        ListenerHelper.convert_to_number_field(tfAnchoPista);
        ListenerHelper.convert_to_number_field(tfAltoPista);
        ListenerHelper.convert_to_number_field(tfViajes);
        ListenerHelper.convert_to_number_field(tfTiempo);
        ListenerHelper.convert_to_number_field(tfTiempoReal);
        
       
       
       
    }
    
    @FXML
    public void createMap(){
        GraphicsContext gc = c_canvas.getGraphicsContext2D();
        //we start verifyng every input
        lNotice.setTextFill(Color.rgb(183, 3, 3));
        if(tfAltoMapa.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese una altura para el mapa!!");
            return;
        }else if(tfAnchoMapa.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese una anchura para el mapa!!");
            return;
        }else if(tfEstaciones.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese una anchura para el mapa!!");
            return;
        //estos se requieren hasta que inicie la simulacion se pueden mover luego
        }else if(tfAltoPista.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese una altura para la pista!!");
            return;
        } else if(tfAnchoPista.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese una anchura para la pista!!");
            return;
        } else if(tfViajes.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese el numero de viajes a simular");
            return;
        }else if(tfTiempo.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese el tiempo a simular");
            return;
        }if(tfTiempoReal.getText().trim().isEmpty()){ 
            lNotice.setText("Por favor ingrese el tiempo que debe durar la simulacion");
            return;
        }
        
        int width = Integer.parseInt(tfAnchoMapa.getText());
        int height = Integer.parseInt(tfAltoMapa.getText());
        int ammountNodes = Integer.parseInt(tfEstaciones.getText());
        
        //create a map the size desired by the user;
        //need some serious serialization for graph acces
        map = new MapConstructor(width, height, 1.0, ammountNodes);
        //free memory from unecesary structures created durign dijkstra
        System.gc();
        GraphDisplay.stopDisplay();
        GraphDisplay.resetDisplay();
        GraphDisplay.startDisplay(gc, c_canvas, map.getNodes(),width,height,System.currentTimeMillis());
        //lNotice.setMinWidth(a_scene.getWidth());
        lNotice.setTextFill(Color.rgb(10, 150, 0));
        lNotice.setText("Mapa creado correctamente!!");
    }
    
}
