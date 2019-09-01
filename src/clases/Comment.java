/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Franklin
 */
public class Comment implements Serializable {
    String text;
    String fecha;
    User user;
    public Comment(){
        this.text=""; this.user=null;
    }
    public Comment(String t,User u){
        this.text=t;this.user=u;
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("MM/yy/dd HH:mm:ss");
        this.fecha=dtf.format(LocalDateTime.now());
    }
    public String Gettext(){
        return this.text;
    }
    public String Getfecha(){
        return this.text;
    }
    public User Getusername(){
        return this.user;
    }
}
