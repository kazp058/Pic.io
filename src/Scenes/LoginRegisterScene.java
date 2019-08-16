/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author KEVIN Z
 */
public class LoginRegisterScene implements ControllableScene{
    
    ScreensController myController;
    
    public LoginRegisterScene(){
    }
           
    @Override
    public Scene getScene() {
        
        HBox hbox = new HBox();
        Label label = new Label("AHHH");
        Button btn = new Button("Puto el que lo lea");
        
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(label);
        
        return new Scene(hbox,myController.getSize()[0],myController.getSize()[1]);
    }    

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }
}
