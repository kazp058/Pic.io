/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Main.MainClass;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author KEVIN Z
 */
public class MainScene implements ControllableScene {

    ScreensController myController;

    private Button checkUsers;
    private Button myGallery;
    private Button createSlideShow;
    private Button createAlbum;

    private Pane main;

    public MainScene() {
        main = imagePane();
    }

    @Override
    public Scene getScene() {

        BorderPane root = new BorderPane();

        root.setCenter(main);
        root.setTop(getTopPane());
        root.setBottom(new HBox());
        root.setRight(new HBox());
        root.setLeft(getLeftPane());

        return new Scene(root, myController.getSize()[0], myController.getSize()[1]);
    }

    @Override
    public void setParent(ScreensController controller) {
        this.myController = controller;
    }

    private HBox getTopPane() {
        HBox top = new HBox();

        Button finder = new Button("Buscar");
        TextField searchField = new TextField();

        top.getChildren().addAll(searchField, finder);

        top.setSpacing(10);
        top.setAlignment(Pos.CENTER_LEFT);
        top.setBackground(new Background(new BackgroundFill(Color.rgb(201, 2, 19), CornerRadii.EMPTY, Insets.EMPTY)));
        top.setPadding(new Insets(10, 0, 10, 20));

        return top;
    }

    private VBox getLeftPane() {
        VBox pane = new VBox();

        ImageView perfImage = new ImageView();

        checkUsers = new Button("Amigos");
        myGallery = new Button("Mi Galeria");
        createSlideShow = new Button("Crear SlideShow");
        createAlbum = new Button("Crear Album");

        checkUsers.setStyle("-fx-font-size:25");
        myGallery.setStyle("-fx-font-size:25");
        createSlideShow.setStyle("-fx-font-size:25");
        createAlbum.setStyle("-fx-font-size:25");

        checkUsers.setOnAction((e) -> {
            main = usersPane();
            myController.setScene(MainClass.mainName);
        });
        myGallery.setOnAction((e) -> {
            main = imagePane();
            myController.setScene(MainClass.mainName);
        });
        createSlideShow.setOnAction((e) -> {
            main = slideShowPane();
            myController.setScene(MainClass.mainName);
        });
        

        checkUsers.setMaxWidth(Double.MAX_VALUE);
        myGallery.setMaxWidth(Double.MAX_VALUE);
        createSlideShow.setMaxWidth(Double.MAX_VALUE);
        createAlbum.setMaxWidth(Double.MAX_VALUE);

        pane.getChildren().addAll(perfImage, myGallery, createAlbum, checkUsers, createSlideShow);

        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setPadding(new Insets(0, 30, 0, 30));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(227, 227, 227), CornerRadii.EMPTY, Insets.EMPTY)));

        return pane;
    }

    private Pane usersPane() {
        Pane pane = new VBox();

        pane.getChildren().add(new Label("Usuariooos"));

        return pane;
    }

    private Pane imagePane() {
        Pane pane = new VBox();

        pane.getChildren().add(new Label("FOOOOOOTOOOOS"));

        return pane;
    }

    private Pane slideShowPane() {
        Pane pane = new VBox();

        pane.getChildren().add(new Label("SLIDEEEEE"));

        return pane;
    }
    
}
