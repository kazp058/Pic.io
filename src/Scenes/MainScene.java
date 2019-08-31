/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Stages.Showable;
import Stages.Slideshow;
import Stages.createAlbum;
import clases.Album;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import clases.Slider;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    ScrollPane mainPane;
    VBox container;

    //Fields
    private ComboBox searchField;

    //Stages
    private Showable createAlbumn;

    @Override
    public Scene getScene() {
        root = new VBox();

        root.getChildren().addAll(getToolBox(), mainPane);
        root.setSpacing(5);
        root.setMinHeight(Control.USE_COMPUTED_SIZE);
        root.setMinWidth(Control.USE_COMPUTED_SIZE);

        if (myController.getCurrentUser().getAlbumes().size() > 0) {
            for (Album album : myController.getCurrentUser().getAlbumes()) {
                container.getChildren().add(getAlbum(album));
            }
        }

        mainPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        mainPane.setContent(container);
        mainPane.setFitToWidth(true);
        container.setSpacing(5);

        return new Scene(root, myController.getSize()[0], myController.getSize()[1]);
    }

    @Override
    public void setParent(ScreensController controller) {
        myController = controller;
        createAlbumn = new createAlbum(myController, this);
    }

    public MainScene() {
        root = new VBox();
        mainPane = new ScrollPane();
        container = new VBox();
        try{
            defaultImage= new Image(new FileInputStream("src/Data/pics/noImage.png"));
        } catch (FileNotFoundException e) {
            defaultImage=null;
        }
    }

    public HBox getAlbum(Album a) {
        HBox album = new HBox();
        VBox sec = new VBox();

        ImageView im = new ImageView();
        ContextMenu albumMenu = getContextMenu(a);

        im.setImage(defaultImage);
        im.setFitWidth(200);
        im.setFitHeight(200);
        im.setPreserveRatio(true);

        Label name = new Label(a.getName());
        Label desc = new Label(a.getDescripcion());
        sec.getChildren().addAll(name, desc);
        album.getChildren().addAll(im, sec);

        sec.setAlignment(Pos.CENTER);
        album.setAlignment(Pos.CENTER);

        album.setOnContextMenuRequested((e) -> {
            albumMenu.show(name, e.getSceneX(), e.getSceneY());
        });

        album.setBackground(new Background(new BackgroundFill(Color.rgb(191, 191, 191), CornerRadii.EMPTY, Insets.EMPTY)));

        return album;
    }

    public HBox getToolBox() {
        HBox toolbox = new HBox();

        searchField = new ComboBox();
        searchField.setEditable(true);

        Button searchButton = new Button("Buscar");
        Button addAlbumn = new Button("Crear Albumn");

        addAlbumn.setOnAction((e) -> {
            createAlbumn.getStage().show();
        });

        toolbox.getChildren().addAll(searchField, searchButton, new HBox(), addAlbumn);
        toolbox.setPadding(new Insets(5, 10, 5, 10));
        toolbox.setSpacing(15);
        toolbox.setBackground(new Background(new BackgroundFill(Color.rgb(201, 2, 19), CornerRadii.EMPTY, Insets.EMPTY)));

        return toolbox;
    }

    private ContextMenu getContextMenu(Album album) {
        ContextMenu cM = new ContextMenu();

        MenuItem addImage = new MenuItem("Añadir Imagen");
        MenuItem edit = new MenuItem("Editar Album");
        MenuItem createSS = new MenuItem("Crear SlideShow");
        MenuItem delete = new MenuItem("Eliminar Album");

        cM.getItems().addAll(addImage, edit, createSS, delete);

        createSS.setOnAction((e)->{
            Slideshow slideshow= new Slideshow(album,1500);
            slideshow.getStage().show();
        });

        return cM;
    }
}
