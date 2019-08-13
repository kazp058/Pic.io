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
public class User implements Serializable{
    String username;
    private String password;
    String name;
    String age;
    protected ArrayList<Album> albumes;
    public User(){
        this("","","","");
    }
    public User(String u, String p){
        this.username=u;this.password=p;
    }
    public User(String u,String p,String n,String a){
        this.username=u;this.password=p;this.name=n;this.age=a;
    }
    public User(String u,String p,String n,String a, ArrayList<Album> al){
        this.username=u;this.password=p;this.name=n;this.age=a; this.albumes=al;
    }
    public void Setpassword(String x){
        this.password=x;
    }
    public void Setname(String x){
        this.name=x;
    }
    public void Setage(String x){
        this.age=x;
    }
    public boolean AddAlbum(Album x){
        ArrayList<String> lista=new ArrayList<>();
        for (Album i:this.albumes){
            lista.add(i.name);
            }
        if (lista.contains(x.name)==false){
            this.albumes.add(x);
        }
        return lista.contains(x.name);
    }
    public void RemoveAlmub(String x){
        for (Album i:this.albumes){
            if (i.name.equals(x)){
                this.albumes.remove(i);
            }
        }
    }
    public String Getusername(){
        return this.username;
    }
    public String Getpassword(){
        return this.password;
    }
    public String Getname(){
        return this.name;
    }
    public String Getage(){
        return this.age;
    }
    public ArrayList<Album> GetAlbumes(){
        return this.albumes;
    }
    public Album GetAlbum(String x){
        for (Album i: this.albumes){
            if (i.name.equals(x)){
                return i;
            }
        }
        return null;
    }
}
