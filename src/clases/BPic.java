/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Franklin
 */
public class BPic extends Button{
    private Pic imagen;
    public BPic(){
        super();
    }
    public BPic(Pic pic){
        super();
        this.imagen=pic;
        super.setGraphic(new ImageView(pic.getImage()));
    }
    public BPic(Pic pic, String msg){
        super(msg);
        this.imagen=pic;
    }
    public Pic getImagen() {
        return imagen;
    }
    public void setImagen(Pic imagen) {
        this.imagen = imagen;
    }
}
