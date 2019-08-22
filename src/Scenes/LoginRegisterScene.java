/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import clases.User;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import toolkit.Serialize;

import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author KEVIN Z
 */
public class LoginRegisterScene implements ControllableScene{
    Font font;
    Background background;
    ScreensController myController;
    
    public LoginRegisterScene(){
    }
    
    public LoginRegisterScene(Font f,Background b){
        font=f; background=b;
    }
           
    @Override
    public Scene getScene() {

        HBox hbox = new HBox();
        Label label = new Label("EEEEEEHHHH");
        Button btn = new Button("Puto el que lo lea");
        
        hbox.setAlignment(Pos.CENTER);

        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(label);

        return new Scene(hbox,myController.getSize()[0],myController.getSize()[1]);
    }    

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }
    private Button crearUser(TextField username,TextField password,TextField name,TextField age){
        Button crear=new Button("Crear Usuario");
        crear.setOnAction((e)->{
            if (username.getText().equals("") || password.getText().equals("")||
                    name.getText().equals("")||age.getText().equals("")){
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Por favor, llene todo los espacios");
                alerta.setTitle("Error");
            } else {
                
            }
        });
        return crear;
    }
}
