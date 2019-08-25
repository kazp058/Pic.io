/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scenes;

import clases.User;
import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author KEVIN Z
 */
public class ScreensController {
    
    private HashMap<String, ControllableScene> screens = new HashMap<>();
    private Scene currentScene;
    private User currentUser;
    private Integer[] size;
    private Stage stage;
    
    public ScreensController(Stage stage){
        this.stage = stage;
    }
    
    public void addScene(String name, ControllableScene scene){
        scene.setParent(this);
        screens.put(name, scene);
    }

    public Stage getStage() {
        return stage;
    }

    public void setScene(String name){
        this.currentScene = screens.get(name).getScene();
    }
    
    public Scene getScene(){
        return this.currentScene;
    }
    
    public void setSize(Integer[] size){
        this.size = size;
    }
    
    public void removeScene(String name){
        screens.remove(name);
    }
    
    public Integer[] getSize(){
        return this.size;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) { this.currentUser=user; }

    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
}
