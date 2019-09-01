package Stages;

import Stages.Showable;
import clases.Album;
import clases.Slider;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Slideshow implements Showable {
    private Album album;
    private int tiempo;
    private ImageView foto;
    private boolean alive;

    public Slideshow(Album album, int tiempo) {
        this.album = album;
        this.tiempo = tiempo;
        this.foto=new ImageView();
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public Stage getStage() {
        Stage slideshow = new Stage();

        Button pausa = new Button("Pausa");
        Button play = new Button("Play");


        VBox boxmaster = new VBox(); boxmaster.setAlignment(Pos.CENTER);
        HBox botones = new HBox();

        botones.getChildren().add(pausa);
        botones.getChildren().add(play);
        botones.setAlignment(Pos.CENTER);

        boxmaster.getChildren().add(botones);
        boxmaster.getChildren().add(foto);

        StackPane root = new StackPane(boxmaster);

        Scene scene = new Scene(root, 1280, 720);

        slideshow.setScene(scene);

        slideshow.setResizable(false);

        Slider slider = new Slider(foto, tiempo, album);
        slider.start();
        alive=true;

        pausa.setOnAction((e) -> {
            if(alive==true){
                slider.suspend();
            }
        });
        play.setOnAction((e) -> {
            if(alive==false){
                slider.resume();
            }
        });
        return slideshow;
    }
}
