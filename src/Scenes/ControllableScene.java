/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import javafx.scene.Scene;
import javafx.scene.text.Font;

/**
 *
 * @author KEVIN Z
 */
public interface ControllableScene {

    public abstract Scene getScene();
    public abstract void setParent(ScreensController controller);

}
