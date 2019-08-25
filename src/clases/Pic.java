/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Franklin
 */
public class Pic implements Serializable {

    private String descripcion;
    private String lugar;
    private String fecha;
    private String reaccion;
    private transient Image image;
    private ArrayList<String> hashtags;
    private ArrayList<Comment> comments;
    private Cam cam;
    private ArrayList<String> etiquetados;

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

    public Pic(File file) {
        image = new Image("file:" + file.getAbsolutePath());
        this.descripcion = "";
        this.lugar = "";
        this.fecha = "";
        this.reaccion = "";
    }

    public String Getdescripcion() {
        return this.descripcion;
    }

    public String Getlugar() {
        return this.lugar;
    }

    public String Getfecha() {
        return this.fecha;
    }

    public String Getreaccion() {
        return this.reaccion;
    }

    public Image GetImage() {
        return this.image;
    }

    public ArrayList<String> Getetiquetados() {
        return this.etiquetados;
    }

    public void Setdescripcion(String x) {
        this.descripcion = x;
    }

    public void Setdlugar(String x) {
        this.lugar = x;
    }

    public void Setfecha(String x) {
        this.fecha = x;
    }

    public void Setreaccion(String x) {
        this.reaccion = x;
    }

    public void SetImage(String x) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(x);
        Image imagen = new Image(in);
        this.image = imagen;
    }

    public ArrayList addetiquetado(String x) {
        /* Esta función devuelve una lista en el que el primer objeto es un bool
        que especifica si se pudo hacer la acción o no, en el caso de que no, el 
        segundo objeto es un string que explica la causa.*/
        boolean valor;
        String causa = "";
        if (this.etiquetados.contains(x)) {
            valor = false;
            causa = "Esta persona ya se encuentra etiquetada";
        } else {
            valor = true;
            this.etiquetados.add(x);
        }
        ArrayList lista = new ArrayList();
        lista.add(valor);
        lista.add(causa);
        return lista;
    }

    public void removeetiquetado(String x) {
        this.etiquetados.remove(x);
    }

    public void addcomment(Comment x) {
        this.comments.add(x);
    }

    public void removecomment(Comment x) {
        this.comments.remove(x);
    }

    public void addhashtag(String x) {
        this.hashtags.add(x);
    }

    public void removehashtag(String x) {
        this.hashtags.remove(x);
    }
}
