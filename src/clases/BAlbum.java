/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javafx.scene.control.Button;

/**
 *
 * @author Franklin
 */
public class BAlbum extends Button {
    private Album album;
    public BAlbum(){
        super();
    }
    public BAlbum(Album album){
        super();
        this.album=album;
    }
    public BAlbum(Album album,String msg){
        super(msg);
        this.album=album;
    }
    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }
    
}
