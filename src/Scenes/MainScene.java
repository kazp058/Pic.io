/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Main.MainClass;
import Stages.Showable;
import Stages.Slideshow;
import Stages.CreateAlbum;
import Stages.EditAlbum;
import Stages.UploadStage;
import clases.Album;
import clases.Pic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javafx.application.Platform;
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
    HBox toolBox;

    //Fields
    private ComboBox searchField;

    //Stages
    private Showable createAlbumn;
    private Showable uploadStage;
    private Showable editAlbum;

    //Variables
    private HashMap<String, ScrollPane> mainPanes;
    private String albumName = "album";
    private String picsName = "pics";

    @Override
    public Scene getScene() {
        root = new VBox();

        root.getChildren().addAll(toolBox, mainPane);
        root.setSpacing(5);
        root.setMinHeight(Control.USE_COMPUTED_SIZE);
        root.setMinWidth(Control.USE_COMPUTED_SIZE);
        setAlbumMainPane();
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
        createAlbumn = new CreateAlbum(myController, this);
        uploadStage = new UploadStage(myController, defaultImage);
        editAlbum = new EditAlbum(myController, this);

    }

    public void setAlbumMainPane() {
        container = new VBox();
        if (myController.getCurrentUser().getAlbumes().size() > 0) {
            for (Album album : myController.getCurrentUser().getAlbumes()) {
                container.getChildren().add(getAlbum(album));
            }
        }
        mainPane.setContent(container);
        container.setSpacing(5);
        toolBox = getToolBox();
    }

    private void setPicMainPane(Album a) {
        container = new VBox();

        if (a.getPics().size() > 0) {
            for (Pic pic : a.getPics()) {
                container.getChildren().add(getPic(pic));
            }
        } else {
            Label msg = new Label("No has agregado imagenes!");
            container.getChildren().add(msg);
        }
        container.setSpacing(5);
        mainPane.setContent(container);

    }

    public MainScene() {
        root = new VBox();
        mainPane = new ScrollPane();
        container = new VBox();
        try {
            defaultImage = new Image(new FileInputStream("src/Data/pics/noImage.png"));
        } catch (FileNotFoundException e) {
            defaultImage = null;
        }
        toolBox = getToolBox();
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

        Label name = new Label("Nombre del Album: \n" + a.getName());
        Label desc = new Label("Descripcion: \n" + a.getDescripcion());
        sec.getChildren().addAll(name, desc);
        album.getChildren().addAll(im, sec);

        sec.setAlignment(Pos.CENTER_LEFT);
        album.setAlignment(Pos.CENTER_LEFT);
        album.setSpacing(10);

        album.setOnContextMenuRequested((e) -> {
            albumMenu.show(name, e.getSceneX(), e.getSceneY());
        });

        album.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        return album;
    }

    public HBox getPic(Pic p) {
        HBox pic = new HBox();
        VBox sec = new VBox();

        ImageView im = new ImageView();

        im.setImage(p.getImage());
        im.setFitWidth(400);
        im.setFitHeight(400);
        im.setPreserveRatio(true);

        Label name = new Label("Nombre:\n\t" + p.getNombre());
        Label desc = new Label("Descripcion:\n\t" + p.getDescripcion());
        Label date;
        if (p.getDate() != null) {
            date = new Label("Fecha:\n\t" + p.getDate().getDay() + "/" + p.getDate().getMonth() + "/" + p.getDate().getYear());
        } else {
            date = new Label("Fecha:\n\tNo hay fecha disponible");
        }
        
        String with = "Personas etiquetadas: ";
        
        for(String t: p.getTags().keySet()){
            with += t + ", " ;
        }
        
        Label comp = new Label(with);
            
        sec.getChildren().addAll(name, desc, date, comp);
        pic.getChildren().addAll(im, sec);

        sec.setAlignment(Pos.TOP_LEFT);
        pic.setAlignment(Pos.CENTER_LEFT);
        pic.setSpacing(10);

        pic.setOnContextMenuRequested((e) -> {
        });

        pic.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        return pic;
    }

    public HBox getToolBox() {
        HBox toolbox = new HBox();

        searchField = new ComboBox();
        searchField.setEditable(true);

        Button searchButton = new Button("Buscar");
        Button addAlbumn = new Button("Crear Albumn");
        Button logOut = new Button("Cerrar Sesion");
        Button goBack = new Button("Regresar");
        Button addImage = new Button("Añadir Imagen");

        addAlbumn.setOnAction((e) -> {
            createAlbumn.getStage().show();
        });

        logOut.setOnAction((e) -> {
            myController.setScene(MainClass.loginName);
        });

        goBack.setOnAction((e) -> {
            setAlbumMainPane();
        });
        addImage.setOnAction((e) -> {
            uploadStage.getStage().show();
        });

        toolbox.getChildren().addAll(searchField, searchButton, new HBox(), addAlbumn, addImage, new HBox(), goBack, new HBox(), logOut);
        toolbox.setPadding(new Insets(5, 10, 5, 10));
        toolbox.setSpacing(15);
        toolbox.setBackground(new Background(new BackgroundFill(Color.rgb(201, 2, 19), CornerRadii.EMPTY, Insets.EMPTY)));

        return toolbox;
    }

    private ContextMenu getContextMenu(Album album) {

        ContextMenu cM = new ContextMenu();

        MenuItem open = new MenuItem("Abrir Album");
        MenuItem addImage = new MenuItem("Añadir Imagen");
        MenuItem edit = new MenuItem("Editar Album");
        MenuItem createSS = new MenuItem("Crear SlideShow");
        MenuItem delete = new MenuItem("Eliminar Album");

        open.setOnAction((e) -> {
            setPicMainPane(album);
        });
        edit.setOnAction((e) -> {
            editAlbum.getStage().show();
        });

        delete.setOnAction((e) -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        myController.getCurrentUser().removeAlbum(album.getName());

                    } catch (Exception e) {
                        setAlbumMainPane();
                    }
                }

            });
        });

        addImage.setOnAction((e) -> {
            uploadStage.getStage().show();
        });

        cM.getItems().addAll(open, addImage, edit, createSS, delete);

        createSS.setOnAction((e) -> {
            Slideshow slideshow = new Slideshow(album, 1500);
            slideshow.getStage().show();
        });
        return cM;
    }

    public VBox getContainer() {
        return container;
    }

}
