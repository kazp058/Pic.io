/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stages;

import Scenes.ScreensController;
import clases.Album;
import clases.Pic;
import clases.Tag;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import toolkit.Serialize;

/**
 *
 * @author KEVIN Z
 */
public class UploadStage implements Showable {

    ScreensController myController;
    Image previewImage;

    public UploadStage(ScreensController myController, Image previewImage) {
        this.myController = myController;
        this.previewImage = previewImage;
        tags = new HashMap<>();
    }

    HashMap<String, Tag> tags;
    private double x;
    private double y;

    @Override
    public Stage getStage() {
        VBox main = new VBox();
        VBox descp = new VBox();
        HBox data = new HBox();
        HBox divider = new HBox();
        HBox comboPane = new HBox();
        HBox mainButtons = new HBox();
        HBox tagger = new HBox();
        Group groupImage = new Group();

        Date date = new Date();

        Button clear = new Button("Limpiar");
        Button save = new Button("Guardar");
        mainButtons.getChildren().addAll(clear, save);
        mainButtons.setSpacing(15);
        mainButtons.setAlignment(Pos.CENTER);

        ScrollPane imagePane = new ScrollPane();
        imagePane.setPrefSize(400, 400);

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

        });

        groupImage.getChildren().addAll(preImage);

        imagePane.setContent(groupImage);
        preImage.setFitWidth(400);
        preImage.setFitHeight(400);
        preImage.setPreserveRatio(true);

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

                preImage.setOnMousePressed((ev) -> {
                    x = ev.getX();
                    y = ev.getY();
                });
                preImage.setOnMouseReleased((ev) -> {
                    Tag temTag = new Tag(x, y, Math.sqrt(Math.pow(ev.getX() - x, 2)), Math.sqrt(Math.pow(ev.getY() - y, 2)));
                    temTag.setPerson(tagF.getText());
                    groupImage.getChildren().add(temTag);
                    tags.put(tagF.getText(), temTag);
                    tagF.setText("");
                });

            } else {
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

        save.setOnAction((e) -> {
            Pic nPic = new Pic(nameI.getText(), descpI.getText(), preImage.getImage(), tags);
            myController.getCurrentUser().getAlbum((String) albumnes.getValue()).addImage(nPic);
            Serialize.actualizarUsuario(myController.getCurrentUser());
            nameI.setText("");
            descpI.setText("");
            albumnes.setValue(null);
            preImage.setImage(previewImage);
            tagF.setText("");
            tags.forEach((s, t) -> {
                groupImage.getChildren().remove(t);
            });
            tags = new HashMap<String, Tag>();

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Imagen guardada con exito!!");
            alerta.setHeaderText("Imagen guardada!");
            alerta.show();
        });

        Scene scene = new Scene(divider, 900, 600);
        Stage stage = new Stage();

        stage.setScene(scene);

        return stage;
    }

}
