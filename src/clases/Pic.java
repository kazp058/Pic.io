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
import java.util.Date;
import java.util.HashMap;
import javafx.scene.image.Image;

/**
 *
 * @author Franklin
 */
public class Pic implements Serializable {

<<<<<<< HEAD
    private String nombre;
    private String descripcion;
    private String lugar;
    private String reaccion;
    private Image image;
    private ArrayList<String> hashtags;
    private ArrayList<Comment> comments;
    private Cam cam;
    private HashMap<String, Tag> tags;

    public Date date;
=======
    public HashMap<String, Tag> getTags() {
        return tags;
    }

    String nombre;
    String descripcion;
    String lugar;
    String reaccion;
    Image image;
    ArrayList<String> hashtags;
    ArrayList<Comment> comments;
    Cam cam;
    HashMap<String, Tag> tags;

    Date date;
>>>>>>> 3cc4533b18e456a57f57a76be604d3bfba106150

    public Pic() {
        this.nombre = "";
        this.descripcion = "";
        this.lugar = "";
        this.reaccion = "";
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pic(String nombre, String descripcion, Image image, HashMap<String, Tag> tags) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.image = image;
        this.tags = tags;
    }

    public String getNombre() {
        return nombre;
    }

    public Pic(String fl) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(fl);
        Image imagen = new Image(in);
        this.image = imagen;
    }

    public Pic(String n, String d, String l, String r, String fl, Cam c) throws FileNotFoundException {
        this.nombre = n;
        this.descripcion = d;
        this.lugar = l;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        this.comments.add(comment);
    }

    public Date getDate() {
        if (date != null) {
            return date;
        } else {
            return null;
        }
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

    public ArrayList<String> getHashtags() {
        return hashtags;
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

    public ArrayList<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }

    public void addHashtag(String x) {
        this.hashtags.add(x);
    }

    public void removeHashtag(String x) {
        this.hashtags.remove(x);
    }
}
