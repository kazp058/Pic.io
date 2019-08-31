package clases;

import clases.Album;
import clases.Pic;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

public class Slider extends Thread {
    private ImageView foto;
    private int tiempo;
    private Album album;
    public Slider(ImageView foto,int tiempo,Album album){
        this.foto=foto; this.tiempo=tiempo ; this.album=album;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void setFoto(ImageView foto) {
        this.foto = foto;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    public void run(){
        for (Pic pic: this.album.getPics()){
            Platform.runLater(()->{
                this.foto.setImage(pic.getImage());
            });
            try {
                sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
