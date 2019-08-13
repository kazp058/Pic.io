/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author Franklin
 */
public class Cam implements Serializable{
    protected String marca;
    protected String modelo;
    public Cam(){
        this("","");
    }
    public Cam(String marca,String modelo){
        this.marca=marca; this.modelo=modelo;
    }
    public void Setmarca(String x){
        this.marca=x;
    }
    public void Setmodelo(String x){
        this.modelo=x;
    }
    public String Getmarca(){
        return this.marca;
    }
    public String Getmodelo(){
        return this.modelo;
    }
}
