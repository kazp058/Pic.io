/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import Scenes.MainScene;
import Scenes.ScreensController;
import clases.Album;
import clases.Pic;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author KEVIN Z
 */
public class MoveAlbum implements Showable {

    ScreensController myController;
    MainScene parent;
    Pic p;
    Album a;

    public MoveAlbum(ScreensController myController, Pic p, Album a, MainScene parent) {
        this.myController = myController;
        this.p = p;
        this.a = a;
        this.parent = parent;
    }

    @Override
    public Stage getStage() {
        Stage s = new Stage();

        VBox main = new VBox();

        ComboBox albumnes = new ComboBox();
        Button mover = new Button("Mover");

        for (Album album : myController.getCurrentUser().getAlbumes()) {
            if (!album.equals(a)) {
                albumnes.getItems().add(album.getName());
            }
        }

        mover.setOnAction((e) -> {
            Album al = myController.getCurrentUser().getAlbum((String) albumnes.getValue());
            al.addImage(p);
            a.getPics().remove(p);

            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setContentText("La informacion ha sido ingresada correctamente");
            alerta.setHeaderText("Foto movida con exito!!!");
            alerta.show();
            
            parent.setAlbumMainPane();
            
            s.close();

        });
        
        main.setSpacing(15);
        main.setAlignment(Pos.TOP_LEFT);

        main.getChildren().addAll(new Label("Mover album"), albumnes, mover);

        s.setScene(new Scene(main));
        return s;
    }

}
