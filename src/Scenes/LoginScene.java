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
import javafx.scene.AccessibleRole;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import toolkit.Serialize;

import java.util.ArrayList;

/**
 *
 * @author KEVIN Z
 */
public class LoginScene implements ControllableScene {

    Font font;
    Background background;
    ScreensController myController;

    public LoginScene() {

    }

    public LoginScene(Font f, Background b) {
        font = f;
        background = b;
    }

    @Override
    public Scene getScene() {
        GridPane labels = new GridPane();
        labels.setHgap(5);
        labels.setVgap(10);
        labels.setAlignment(Pos.CENTER);

        TextField username = textBox();
        PasswordField password = passwordBox();
        Text labelUser = textLabel("Username:");
        Text labelPass = textLabel("Contraseña:");
        labels.add(labelUser, 0, 0);
        labels.add(username, 1, 0);
        labels.add(labelPass, 0, 1);
        labels.add(password, 1, 1);

        HBox botones = new HBox();
        botones.setSpacing(25);
        botones.setAlignment(Pos.CENTER);

        Button iniciar = iniciarSesion(username, password);
        Button crear = crearUsuario();
        botones.getChildren().add(iniciar);
        botones.getChildren().add(crear);

        VBox boxmaster = new VBox();
        boxmaster.setSpacing(15);
        boxmaster.setAlignment(Pos.CENTER);

        boxmaster.getChildren().add(labels);
        boxmaster.getChildren().add(botones);

        StackPane root = new StackPane(boxmaster);
        root.setBackground(background);
        return new Scene(root, myController.getSize()[0], myController.getSize()[1]);
    }

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }

    private Button iniciarSesion(TextField username, TextField password) {
        Button iniciar = new Button("Iniciar Sesión");
        iniciar.setFont(font);
        iniciar.setAlignment(Pos.CENTER);
        iniciar.setOnAction((ActionEvent e) -> {

            User user = new User(username.getText(), password.getText());
            ArrayList<Object> resultado = new Serialize().iniciarSesion(user);
            if ((boolean) resultado.get(0)) {
                User usuario = (User) resultado.get(1);
                this.myController.setCurrentUser(usuario);
                myController.setScene(MainClass.mainName);
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("No se pudo encontrar el usuario");
                alerta.setHeaderText("Error al intentar iniciar sesión");
                alerta.show();
                username.clear();
                password.clear();
            }

        });
        return iniciar;
    }

    private Button crearUsuario() {
        Button crear = new Button("Crear Usuario");
        crear.setFont(font);
        crear.setAlignment(Pos.CENTER);
        crear.setOnAction((e) -> {
            myController.setScene(MainClass.registerName);
        });
        return crear;
    }

    private Text textLabel(String mensaje) {
        Text texto = new Text(mensaje);
        texto.setFont(font);
        texto.setFill(Color.WHITE);
        texto.setStrokeType(StrokeType.OUTSIDE);
        texto.setStroke(Color.BLACK);
        texto.setStrokeWidth(1);
        texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }

    private TextField textBox() {
        TextField texto = new TextField();
        texto.setAlignment(Pos.CENTER_LEFT);
        texto.setFont(font);
        texto.setMinSize(150, 30);
        return texto;
    }

    private PasswordField passwordBox() {
        PasswordField texto = new PasswordField();
        texto.setAlignment(Pos.CENTER_LEFT);
        texto.setMinSize(150, 30);
        return texto;
    }
}
