/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Stages.Showable;
import Stages.createAlbum;
import clases.Album;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
=======
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
>>>>>>> c3d694a2957549919d3312a6849a70a1162462c6
import javafx.stage.Stage;

/**
 *
 * @author KEVIN Z
 */
public class MainScene implements ControllableScene {

    private ScreensController myController;
    private Image defaultImage;

    //Panes
    VBox root;

    //Fields
    private ComboBox searchField;
    
    //Stages
    private Showable createAlbumn;

    @Override
    public Scene getScene() {
        root = new VBox();

        root.getChildren().add(getToolBox());

        if (myController.getCurrentUser().getAlbumes().size() > 0) {
            for (Album album : myController.getCurrentUser().getAlbumes()) {
                root.getChildren().add(getAlbum(album));
            }
        }
        return new Scene(root, myController.getSize()[0], myController.getSize()[1]);
    }

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }

    public MainScene() {
        root = new VBox();

        FileInputStream input = null;
        try {
            input = new FileInputStream("src/Data/pics/noImage.jpg");
            input.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainSceneV1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
        defaultImage = new Image(input);
        
        this.createAlbumn = new createAlbum(myController);

    }

    public HBox getAlbum(Album a) {
        HBox album = new HBox();
        VBox sec = new VBox();

        ImageView im = new ImageView(defaultImage);
        Label name = new Label(a.getName());
        Label desc = new Label(a.getDescripcion());
        sec.getChildren().addAll(name, desc);
        album.getChildren().addAll(im, sec);

        sec.setAlignment(Pos.CENTER);
        album.setAlignment(Pos.CENTER);

        return album;
    }

    public HBox getToolBox() {
        HBox toolbox = new HBox();

        searchField = new ComboBox();
        searchField.setEditable(true);

        Button searchButton = new Button("Buscar");
        Button addAlbumn = new Button("Crear Albumn");
        
        addAlbumn.setOnAction((e) ->{
            createAlbumn.getStage().show();
        });

        toolbox.getChildren().addAll(searchField, searchButton, new HBox() , addAlbumn);
        toolbox.setPadding(new Insets(5, 10, 5, 10));
        toolbox.setSpacing(15);

        return toolbox;
    }
    private Stage Slideshow(Album album, int tiempo){
        Stage slideshow= new Stage();

        Button pausa= new Button("Pausa"); Button play= new Button("Play");

        ImageView imageView=new ImageView();

        VBox boxmaster= new VBox(); HBox botones= new HBox();

        botones.getChildren().add(pausa); botones.getChildren().add(play); botones.setAlignment(Pos.CENTER);

        boxmaster.getChildren().add(botones); boxmaster.getChildren().add(imageView);

        StackPane root= new StackPane(boxmaster);

        Scene scene= new Scene(root,720,1280);

        slideshow.setScene(scene);

        slideshow.setResizable(false);

        Slider slider=new Slider(imageView,tiempo,album);
        slider.start();

        pausa.setOnAction((e)->{
            try {
                slider.wait();
            } catch (InterruptedException ex) {
            }
        });
        play.setOnAction((e)->{
            slider.notify();
        });
        return slideshow;
    }
}
