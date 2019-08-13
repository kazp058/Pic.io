/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Franklin
 */
public class Album implements Serializable{
    String name;
    String descripcion;
    String username;
    public ArrayList<Pic> pics;
    public Album(){
        this("","","");
    }
    public Album(String n,String d,String u){
        this.name=n;this.descripcion=d;this.username=u;
    }
    public void Setname(String x){
        this.name=x;
    }
    public void Setdescripcion(String x){
        this.descripcion=x;
    }
    public void Setusername(String x){
        this.username=x;
    }
    public String Getname(){
        return this.name;
    }
    public String Getdescripcion(){
        return this.descripcion;
    }
    public String Getusername(){
        return this.username;
    }
}
