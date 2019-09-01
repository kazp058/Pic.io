/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import Scenes.MainScene;
import Scenes.ScreensController;
import clases.Album;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KEVIN Z
 */
public class EditAlbum implements Showable {

    ScreensController myController;
    MainScene parent;

    public EditAlbum(ScreensController myController, MainScene parent) {
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

        ComboBox albumnes = new ComboBox();

        for (Album album : myController.getCurrentUser().getAlbumes()) {
            albumnes.getItems().add(album.getName());
        }

        nameBox.getChildren().addAll(nameL, albumnes);

        VBox descBox = new VBox();

        TextField newName = new TextField();

        Label descL = new Label("Descripcion");
        TextField descT = new TextField();

        descBox.getChildren().addAll(descL, descT);

        main.getChildren().addAll(nameBox, newName ,descBox, buttonBoxes);

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
            newName.setText("");
            descT.setText("");
            albumnes.setValue(null);
        });

        save.setOnAction((e) -> {

            stage.setAlwaysOnTop(false);
            Album a = myController.getCurrentUser().getAlbum((String) albumnes.getValue());
            a.setName(newName.getText());
            a.setDescripcion(descT.getText());

            newName.setText("");
            descT.setText("");
            albumnes.setValue(null);
            
            parent.setAlbumMainPane();
            stage.close();

        });

        stage.setScene(new Scene(main));
        return stage;
    }
}
