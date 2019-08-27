/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author Franklin
 */
public class Album implements Serializable {

    String name;
    String descripcion;
    String username;
    public ArrayList<Pic> pics;

    public Album() {
        this("", "", "");
    }

    public Album(String n, String d, String u) {
        this.name = n;
        this.descripcion = d;
        this.username = u;
    }

    public void setName(String x) {
        this.name = x;
    }

    public void setDescripcion(String x) {
        this.descripcion = x;
    }

    public void setUsername(String x) {
        this.username = x;
    }

    public String getName() {
        return this.name;
    }
    
    public void addImage(Pic pic){
        pics.add(pic);
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public String getUsername() {
        return this.username;
    }
}
