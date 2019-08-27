/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author Franklin
 */
public class Pic implements Serializable {

    String descripcion;
    String lugar;
    String fecha;
    String reaccion;
    Image image;
    ArrayList<String> hashtags;
    ArrayList<Comment> comments;
    Cam cam;
    HashMap<String, Tag> tags;

    public Pic() {
        this.descripcion = "";
        this.lugar = "";
        this.fecha = "";
        this.reaccion = "";
    }

    public Pic(String fl) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(fl);
        Image imagen = new Image(in);
        this.image = imagen;
    }

    public Pic(String d, String l, String f, String r, String fl, Cam c) throws FileNotFoundException {
        this.descripcion = d;
        this.lugar = l;
        this.fecha = f;
        this.reaccion = r;
        this.cam = c;
        FileInputStream in = new FileInputStream(fl);
        Image imagen = new Image(in);
        this.image = imagen;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getLugar() {
        return this.lugar;
    }

    public String getFecha() {
        return this.fecha;
    }

    public String getReaccion() {
        return this.reaccion;
    }

    public Image getImage() {
        return this.image;
    }

    public void setDescripcion(String x) {
        this.descripcion = x;
    }

    public void setLugar(String x) {
        this.lugar = x;
    }

    public void setFecha(String x) {
        this.fecha = x;
    }

    public void setReaccion(String x) {
        this.reaccion = x;
    }

    public void setImage(String x) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(x);
        Image imagen = new Image(in);
        this.image = imagen;
    }

    public void addComment(Comment x) {
        this.comments.add(x);
    }

    public void removeComment(Comment x) {
        this.comments.remove(x);
    }

    public void addHashtag(String x) {
        this.hashtags.add(x);
    }

    public void removeHashtag(String x) {
        this.hashtags.remove(x);
    }
}
