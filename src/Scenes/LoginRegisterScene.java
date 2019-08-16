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

/**
 *
 * @author KEVIN Z
 */
public class LoginRegisterScene implements ControllableScene{
    Font font;
    Background background;
    ScreensController myController;
    
    public LoginRegisterScene(Font f,Background b){
        font=f; background=b;
    }
           
    @Override
    public Scene getScene() {

        HBox hbox = new HBox();
        Label label = new Label("AHHH");

        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(label);

        return new Scene(hbox,myController.getSize()[0],myController.getSize()[1]);
    }    

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }
    private Button iniciarSesion(String username, String password){
        Button iniciar=new Button("Iniciar Sesión");
        iniciar.setFont(font); iniciar.setAlignment(Pos.CENTER);
        iniciar.setOnAction((ActionEvent e) ->{
            
            User user=new User(username,password);
            ArrayList<Object> resultado=new Serialize().iniciarSesion(user);
            if ((boolean) resultado.get(0)) {
                User usuario = (User) resultado.get(1);
                this.myController.setCurrentUser(usuario);
            }

        });
        return iniciar;
    }
}
