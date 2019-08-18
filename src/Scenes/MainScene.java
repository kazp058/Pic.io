/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author KEVIN Z
 */
public class MainScene implements ControllableScene{
    
    ScreensController myController;
    
    public MainScene(){
    }

    @Override
    public Scene getScene() {

        BorderPane root = new BorderPane();
        
        HBox hbox = new HBox();
        Label label = new Label("AHHHHHHHHHHHHHHHHHH");
        
        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        
        root.setCenter(hbox);
        root.setTop(getTopPane());
        root.setBottom(new HBox());
        root.setRight(new HBox());
        root.setLeft(new HBox());
        
        return new Scene(root, myController.getSize()[0],myController.getSize()[1]);
    }

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }
    
    private HBox getTopPane(){
        HBox top = new HBox();
        
        Button finder = new Button("Buscar");
        TextField searchField = new TextField();
        
        top.getChildren().addAll(searchField, finder);
        
        top.setSpacing(10);
        top.setAlignment(Pos.CENTER_LEFT);
        top.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        
        
        return top;
    }
    
}
