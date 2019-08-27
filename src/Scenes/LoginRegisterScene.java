/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Main.MainClass;
import clases.User;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import toolkit.Serialize;
import toolkit.Tool;

import java.util.ArrayList;


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
        VBox boxmaster=new VBox(); boxmaster.setAlignment(Pos.CENTER); boxmaster.setSpacing(15);

        GridPane labels= new GridPane(); labels.setHgap(5); labels.setVgap(10); labels.setAlignment(Pos.CENTER);

        Text labelUser= textLabel("Username:"); TextField username= textBox();
        Text labelPass= textLabel("Contraseña:"); PasswordField password= passwordBox();
        Text labelName= textLabel("Nombre:"); TextField name= textBox();
        Text labelAge= textLabel("Edad:"); TextField age= textBox();

        labels.add(labelUser,0,0); labels.add(username,1,0);
        labels.add(labelPass,0,1); labels.add(password,1,1);
        labels.add(labelName,0,2); labels.add(name,1,2);
        labels.add(labelAge,0,3); labels.add(age,1,3);

        HBox botones= new HBox(); botones.setSpacing(25); botones.setAlignment(Pos.CENTER);

        Button volver= volver() ;Button crear= crearUser(username,password,name,age);

        botones.getChildren().add(volver); botones.getChildren().add(crear);

        boxmaster.getChildren().add(labels);
        boxmaster.getChildren().add(botones);

        StackPane root=new StackPane(boxmaster); root.setBackground(background);

        return new Scene(root,myController.getSize()[0],myController.getSize()[1]);
    }    

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }
    private Button crearUser(TextField username,TextField password,TextField name,TextField age){
        Button crear=new Button("Crear Usuario"); crear.setAlignment(Pos.CENTER);crear.setFont(font);
        crear.setOnAction((e)->{
            if (username.getText().equals("") || password.getText().equals("")||
                    name.getText().equals("")||age.getText().equals("")){
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Por favor, llene todo los espacios");
                alerta.setTitle("Error");
            } else if(new Tool().isNumeric(age.getText())){
                Alert alerta=new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText("Por favor, en su edad ponga un número. No se ponga chistoso, esto es serio.");
                alerta.setTitle("Error, pero entre muchas comillas.");
                age.setText("");
            }else {
                Serialize tools=new Serialize();
                User usuario=new User(username.getText(),password.getText(),name.getText(),age.getText());
                ArrayList resultado= tools.crearUsuario(usuario);
                if ((boolean)resultado.get(0)){
                    Alert exito= new Alert(Alert.AlertType.CONFIRMATION);
                    exito.setHeaderText((String)resultado.get(1));
                    myController.setScene(MainClass.loginName);
                } else{
                    Alert error= new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText((String)resultado.get(1));
                    username.setText(""); password.setText(""); name.setText(""); age.setText("");
                }
            }
        });
        return crear;
    }
    private Button volver(){
        Button volver=new Button("Regresar"); volver.setAlignment(Pos.CENTER); volver.setFont(font);
        volver.setOnAction((e)->{
            myController.setScene(MainClass.loginName);
        });
        return volver;
    }
    private Text textLabel(String mensaje){
        Text texto= new Text(mensaje);
        texto.setFont(font); texto.setFill(Color.WHITE); texto.setStrokeType(StrokeType.OUTSIDE);
        texto.setStroke(Color.BLACK); texto.setStrokeWidth(1); texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }
    private TextField textBox(){
        TextField texto=new TextField();
        texto.setAlignment(Pos.CENTER_LEFT); texto.setFont(font);
        texto.setMinSize(150,30);
        return texto;
    }
    private PasswordField passwordBox(){
        PasswordField texto=new PasswordField();
        texto.setAlignment(Pos.CENTER_LEFT);
        texto.setMinSize(150,30);
        return texto;
    }
}
