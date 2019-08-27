/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import Main.MainClass;
import clases.Album;
import clases.BAlbum;
import clases.Pic;
import clases.Tag;
import java.util.Arrays;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
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

    private double x;
    private double y;
    private HashMap<String, Tag> tags;

    private VBox main = usersPane();
    private BorderPane root;

    private Stage uploadStage;
    private Stage cAStage;

    private Image previewImage;

    public MainScene() {
        root = new BorderPane();
        tags = new HashMap<String, Tag>();

        uploadStage = new Stage();
        uploadStage.setTitle("Añadir imagen");
        uploadStage.toFront();
        uploadStage.initStyle(StageStyle.UTILITY);
        uploadStage.setAlwaysOnTop(true);

        cAStage = new Stage();
        cAStage.setTitle("Crear Album");
        cAStage.toFront();
        cAStage.initStyle(StageStyle.UTILITY);
        cAStage.setAlwaysOnTop(true);

        FileInputStream input = null;
        try {
            input = new FileInputStream("src/Data/pics/noImage.jpg");
            System.out.println(input);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
        }
        previewImage = new Image(input);

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
        ComboBox search = new ComboBox();
        search.setEditable(true);

        top.getChildren().addAll(search, finder);

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
        Button logOut = new Button("Cerrar Sesion");

        checkUsers.setStyle("-fx-font-size:25");
        myGallery.setStyle("-fx-font-size:25");
        createSlideShow.setStyle("-fx-font-size:25");
        createAlbum.setStyle("-fx-font-size:25");
        logOut.setStyle("-fx-font-size:25");

        checkUsers.setOnAction((e) -> {
            main = usersPane();
            root.setCenter(main);
        });

        createAlbum.setOnAction((e) -> {
            cAStage.setScene(getCreatorScene());

            cAStage.show();
        });

        myGallery.setOnAction((e) -> {
            root.setCenter(imagePane());
        });

        createSlideShow.setOnAction((e) -> {
            main = slideShowPane();
            root.setCenter(main);
        });

        logOut.setOnAction((e) -> {
            myController.setScene(MainClass.loginName);
        });

        checkUsers.setMaxWidth(Double.MAX_VALUE);
        myGallery.setMaxWidth(Double.MAX_VALUE);
        createSlideShow.setMaxWidth(Double.MAX_VALUE);
        createAlbum.setMaxWidth(Double.MAX_VALUE);

        pane.getChildren().addAll(perfImage, myGallery, createAlbum, checkUsers, createSlideShow);
        addSpace(pane);
        pane.getChildren().add(logOut);

        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(10);
        pane.setPadding(new Insets(0, 30, 0, 30));
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(227, 227, 227), CornerRadii.EMPTY, Insets.EMPTY)));

        return pane;
    }

    private VBox usersPane() {
        VBox pane = new VBox();

        pane.getChildren().add(new Label("Usuariooos"));
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    private void addSpace(VBox box) {
        Region spacer = new Region(); // spacer
        spacer.setPrefWidth(50);
        box.getChildren().add(spacer);
    }

    private ScrollPane imagePane() {
        FlowPane container = new FlowPane();
        ScrollPane m = new ScrollPane();
        AnchorPane a = new AnchorPane();

        container.setPrefSize(m.getPrefWidth(), m.getPrefHeight());
        container.setPadding(new Insets(10, 15, 0, 15));

        Button addImage = new Button("Añadir Imagen");

        addImage.setStyle("-fx-font-size:25");
        addImage.setMaxWidth(Double.MAX_VALUE);

        container.getChildren().add(addImage);

        addImage.setOnAction((e) -> {
            System.out.println(myController.getCurrentUser().getAlbumes());
            if (!myController.getCurrentUser().getAlbumes().isEmpty()) {
                uploadStage.setScene(getUploadScene());

                uploadStage.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Crea un album antes de crear una imagen!");
                alerta.setHeaderText("Error al añadir imagen");
                alerta.show();
            }
        });

        try {
            for (Album album : myController.getCurrentUser().getAlbumes()) {
                BAlbum Albumview = new BAlbum(album, album.getName());
            }
        } catch (NullPointerException e) {
            Label label = new Label("Qué triste, no tienes albumes. Pero tranquilo, ¡Agrega uno!");
        }

        container.setAlignment(Pos.TOP_CENTER);

        m.setHbarPolicy(ScrollBarPolicy.NEVER);
        m.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        m.setContent(container);
        m.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);

        return m;
    }

    private VBox slideShowPane() {
        VBox pane = new VBox();

        pane.getChildren().add(new Label("SLIDEEEEE"));
        pane.setAlignment(Pos.CENTER);

        return pane;
    }

    private Button generateAlbum(Album album) {
        Button btn = new Button(album.getName());
        btn.setMaxWidth(Double.MAX_VALUE);
        return btn;
    }

    private Scene getCreatorScene() {

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

            cAStage.setAlwaysOnTop(false);
            boolean val = myController.getCurrentUser().addAlbum(new Album(nameT.getText(), descT.getText(), myController.getCurrentUser().getUsername()));
            System.out.println(val);
            if (val) {
                uploadStage.setAlwaysOnTop(false);
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setContentText("La informacion ha sido ingresada correctamente");
                alerta.setHeaderText("Album creado con exito");
                alerta.show();

            } else {
                uploadStage.setAlwaysOnTop(false);
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Elija otro nombre para su album");
                alerta.setHeaderText("El Album ya existe");
                alerta.show();

            }

            nameT.setText("");
            descT.setText("");

        });

        return new Scene(main, 400, 200);
    }

    private Scene getUploadScene() {

        VBox main = new VBox();
        VBox descp = new VBox();
        HBox data = new HBox();
        HBox divider = new HBox();
        HBox comboPane = new HBox();
        HBox mainButtons = new HBox();
        HBox tagger = new HBox();
        Group groupImage = new Group();

        Button clear = new Button("Limpiar");
        Button save = new Button("Guardar");
        mainButtons.getChildren().addAll(clear, save);
        mainButtons.setSpacing(15);
        mainButtons.setAlignment(Pos.CENTER);

        ScrollPane imagePane = new ScrollPane();
        imagePane.setPrefSize(600, 600);

        Label tagL = new Label("Nombre");
        TextField tagF = new TextField();
        Button startTag = new Button("Tag");
        tagger.getChildren().addAll(tagL, tagF, startTag);

        Label desc = new Label("Descripcion");
        desc.setStyle("-fx-font-size:15");
        TextField descpI = new TextField();

        Label name = new Label("Nombre ");
        name.setStyle("-fx-font-size:15");
        TextField nameI = new TextField();

        ComboBox albumnes = new ComboBox();

        for (Album album : myController.getCurrentUser().getAlbumes()) {
            albumnes.getItems().add(album.getName());
        }

        comboPane.getChildren().addAll(new Label("Seleccionar Album"), albumnes);
        comboPane.setSpacing(5);

        Button choseFiles = new Button("Seleccionar imagen");

        ImageView preImage = new ImageView();
        preImage.setImage(previewImage);

        choseFiles.setOnAction((e) -> {

            this.uploadStage.setAlwaysOnTop(false);

            FileChooser fc = new FileChooser();
            fc.setTitle("Selecciona una imagen");
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
            );
            File imageFile = fc.showOpenDialog(myController.getStage());

            if (imageFile != null) {
                Image image = new Image("file:" + imageFile.getAbsolutePath());
                preImage.setImage(image);

            } else {
                preImage.setImage(previewImage);
            }

            this.uploadStage.setAlwaysOnTop(true);

        });

        groupImage.getChildren().addAll(preImage);

        imagePane.setContent(groupImage);

        descp.getChildren().addAll(desc, descpI);
        descp.setAlignment(Pos.CENTER);
        descp.setSpacing(10);

        data.getChildren().addAll(name, nameI);
        main.getChildren().addAll(data, descp, comboPane, choseFiles, tagger, mainButtons);

        main.setAlignment(Pos.TOP_CENTER);
        data.setAlignment(Pos.CENTER);
        data.setSpacing(20);
        main.setSpacing(25);

        divider.getChildren().addAll(imagePane, main);

        divider.setSpacing(15);

        divider.setPadding(new Insets(10, 15, 0, 15));

        name.setAlignment(Pos.CENTER_RIGHT);
        nameI.setAlignment(Pos.CENTER);

        startTag.setOnAction((e) -> {
            if (!tagF.getText().equals("") && !tags.containsKey(tagF.getText())) {
                uploadStage.setAlwaysOnTop(false);

                preImage.setOnMousePressed((ev) -> {
                    x = ev.getX();
                    y = ev.getY();

                    System.out.println("X:" + x + " Y:" + y);
                });
                preImage.setOnMouseReleased((ev) -> {
                    Tag temTag = new Tag(x, y, Math.sqrt(Math.pow(ev.getX() - x, 2)), Math.sqrt(Math.pow(ev.getY() - y, 2)));
                    System.out.println(temTag);
                    temTag.setPerson(tagF.getText());
                    groupImage.getChildren().add(temTag);
                    tags.put(tagF.getText(), temTag);
                    tagF.setText("");
                });

            } else {
                uploadStage.setAlwaysOnTop(false);

                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("Ingrese un nombre en el campo antes de presionar el boton");
                alerta.setHeaderText("Falta de campo!");
                alerta.show();
            }
        });

        clear.setOnAction((e) -> {
            nameI.setText("");
            descpI.setText("");
            albumnes.setValue(null);
            preImage.setImage(previewImage);
            tagF.setText("");
            tags.forEach((s, t) -> {
                groupImage.getChildren().remove(t);
            });
            tags = new HashMap<String, Tag>();
        });
        
        save.setOnAction((e)->{
            myController.getCurrentUser().getAlbum((String) albumnes.getValue()).addImage(new Pic());
        });

        Scene scene = new Scene(divider, 900, 600);
        return scene;
    }
}
