/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Main.MainClass;
import clases.Album;
import clases.BAlbum;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    private Pane main = usersPane();
    private BorderPane root;
    
    private Stage uploadStage;

    public MainScene() {
        root = new BorderPane();
    }

    @Override
    public Scene getScene() {

        root = new BorderPane();

        root.setCenter(main);
        root.setTop(getTopPane());
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
        createAlbum = new Button("Añadir imagen");

        checkUsers.setStyle("-fx-font-size:25");
        myGallery.setStyle("-fx-font-size:25");
        createSlideShow.setStyle("-fx-font-size:25");
        createAlbum.setStyle("-fx-font-size:25");

        checkUsers.setOnAction((e) -> {
            main = usersPane();
            root.setCenter(main);
        });

        createAlbum.setOnAction((e) -> {

            uploadStage = new Stage();
            uploadStage.setTitle("Añadir imagen");
            uploadStage.toFront();
            
            uploadStage.initStyle(StageStyle.UTILITY);
            uploadStage.setAlwaysOnTop(true);


            uploadStage.setScene(getUploadScene());
            uploadStage.show();

        });

        myGallery.setOnAction((e) -> {
            root.setCenter(imagePane());
        });

        createSlideShow.setOnAction((e) -> {
            main = slideShowPane();
            root.setCenter(main);
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
        VBox pane = new VBox();

        pane.getChildren().add(new Label("Usuariooos"));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private ScrollPane imagePane() {
        VBox pane = new VBox();
        ScrollPane m = new ScrollPane();

        pane.setPadding(new Insets(5, 15, 0, 15));
        Button addAlbum = new Button("Añadir imagen");

        addAlbum.setStyle("-fx-font-size:25");
        addAlbum.setMaxWidth(Double.MAX_VALUE);

        pane.getChildren().add(addAlbum);

        try {
            for (Album album : myController.getCurrentUser().getAlbumes()) {
                BAlbum Albumview = new BAlbum(album, album.Getname());
                pane.getChildren().add(Albumview);
            }
        } catch (NullPointerException e) {
            Label label = new Label("Qué triste, no tienes albumes. Pero tranquilo, ¡Agrega uno!");
            pane.getChildren().add(label);

        }
        m.setContent(pane);
        m.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        return m;
    }

    private Pane slideShowPane() {
        Pane pane = new VBox();

        pane.getChildren().add(new Label("SLIDEEEEE"));

        return pane;
    }

    private Button generateAlbum(Album album) {
        Button btn = new Button(album.Getname());
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    private Scene getUploadScene() {

        VBox main = new VBox();
        VBox descp = new VBox();
        HBox data = new HBox(); 
        
        Label desc = new Label("Descripcion");
        desc.setStyle("-fx-font-size:15");
        TextField descpI = new TextField();
        Label name = new Label("Nombre de la foto");
        name.setStyle("-fx-font-size:15");
        TextField nameI = new TextField();
        Button choseFiles = new Button("Seleccionar imagen");

        choseFiles.setOnAction((e) -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Selecciona una imagen");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
            );
            File image = fc.showOpenDialog(myController.getStage());
            
        });
        
        descp.getChildren().addAll(desc, descpI);
        descp.setAlignment(Pos.CENTER);
        descp.setSpacing(10);

        data.getChildren().addAll(name, nameI);
        main.getChildren().addAll(data, descp ,choseFiles);
        
        main.setAlignment(Pos.TOP_CENTER);
        data.setAlignment(Pos.CENTER);
        data.setSpacing(20);
        main.setSpacing(25);
        
        main.setPadding(new Insets(10,15,0,15));

        name.setAlignment(Pos.CENTER_RIGHT);
        nameI.setAlignment(Pos.CENTER);

        Scene scene = new Scene(main, 800, 600);
        return scene;
    }
    /*private Button agregarAlbum(){
        
    }*/
}
