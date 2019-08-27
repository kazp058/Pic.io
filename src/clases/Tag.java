/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author KEVIN Z
 */
public class Tag extends Rectangle {

    String person;

    public Tag(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.BLACK);
        this.setStrokeWidth(5);
    }

    public void setPerson(String person) {
        this.person = person;
    }

}
