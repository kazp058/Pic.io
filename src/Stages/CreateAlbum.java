/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import Main.MainClass;
import Scenes.MainScene;
import Scenes.ScreensController;
import clases.Album;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KEVIN Z
 */
public class CreateAlbum implements Showable {

    ScreensController myController;
    MainScene parent;

    public CreateAlbum(ScreensController myController, MainScene parent) {
        this.myController = myController;
        this.parent = parent;
    }

    @Override
    public Stage getStage() {
        Stage stage = new Stage();
        Button clean = new Button("Limpiar");
        Button save = new Button("Crear");

        VBox main = new VBox();
        HBox nameBox = new HBox();
        HBox buttonBoxes = new HBox();

        buttonBoxes.getChildren().addAll(clean, save);

        Label nameL = new Label("Nombre");
        TextField nameT = new TextField();

        nameBox.getChildren().addAll(nameL, nameT);

        VBox descBox = new VBox();

        Label descL = new Label("Descripcion");
        TextField descT = new TextField();

        descBox.getChildren().addAll(descL, descT);

        main.getChildren().addAll(nameBox, descBox, buttonBoxes);

        main.setSpacing(15);
        main.setPadding(new Insets(5, 15, 5, 15));
        main.setAlignment(Pos.CENTER);

        nameBox.setSpacing(15);
        nameBox.setAlignment(Pos.CENTER);

        buttonBoxes.setSpacing(15);
        buttonBoxes.setAlignment(Pos.CENTER);

        descBox.setSpacing(15);
        descBox.setAlignment(Pos.CENTER);

        clean.setOnAction((e) -> {
            nameT.setText("");
            descT.setText("");
        });

        save.setOnAction((e) -> {

            stage.setAlwaysOnTop(false);
            try {
                Album a = new Album(nameT.getText(), descT.getText(), myController.getCurrentUser().getUsername());
                boolean val = myController.getCurrentUser().addAlbum(a);
                if (val) {
                    stage.setAlwaysOnTop(false);
                    Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setContentText("La informacion ha sido ingresada correctamente");
                    alerta.setHeaderText("Album creado con exito");
                    alerta.show();
                    parent.getContainer().getChildren().add(parent.getAlbum(a));

                } else {
                    stage.setAlwaysOnTop(false);
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setContentText("Elija otro nombre para su album");
                    alerta.setHeaderText("El Album ya existe");
                    alerta.show();

                }
            } catch (Exception ex) {
                System.out.println(ex);
            }

            nameT.setText("");
            descT.setText("");

        });

        stage.setScene(new Scene(main));
        return stage;
    }

}
