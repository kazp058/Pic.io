package Stages;

import clases.Comment;
import clases.Pic;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Foto implements Showable {
    private Pic foto;

    public Foto(Pic foto) {
        this.foto = foto;
    }

    public Pic getFoto() {
        return foto;
    }

    public void setFoto(Pic foto) {
        this.foto = foto;
    }

    @Override
    public Stage getStage() {
        Stage stage=new Stage();

        VBox boxmaster= new VBox();

        ImageView imagen=new ImageView(foto.getImage());
        boxmaster.getChildren().add(imagen);

        VBox comentarios_espacio=new VBox(); comentarios_espacio.setAlignment(Pos.CENTER);
        VBox comentarios= new VBox(); comentarios.setAlignment(Pos.CENTER_LEFT); comentarios.setSpacing(10);

        if (!foto.getDescripcion().equals("")){
            boxmaster.getChildren().add(new Label(foto.getDescripcion()));
        }

        if (!(foto.getHashtags().size()==0)){
            HBox labels=new HBox(); labels.setSpacing(10);
            for (String tag: foto.getHashtags()){
                labels.getChildren().add(new Label(tag));
            }
            ScrollPane hashtags= new ScrollPane(labels);
            hashtags.setFitToWidth(true); hashtags.setFitToHeight(true);
            boxmaster.getChildren().add(hashtags);
        }

        if (!(foto.getComments().size()==0)){
            for (Comment i: foto.getComments()){
                comentarios.getChildren().add(new Label(i.Gettext()));
            }
        }
        TextField comentario = new TextField();
        comentario.setOnKeyPressed((e)->{
            if (e.getCode()== KeyCode.ENTER){
                comentarios.getChildren().add(new Label(comentario.getText()));
                comentario.setText("");
            }
        });
        comentarios_espacio.getChildren().add(comentarios);
        comentarios_espacio.getChildren().add(comentario);
        boxmaster.getChildren().add(new Label("Comentarios"));
        boxmaster.getChildren().add(comentarios_espacio);

        StackPane root = new StackPane(boxmaster);

        Scene scene = new Scene(root, 1280, 720);

        stage.setScene(scene);

        stage.setResizable(false);
        return stage;
    }
}
