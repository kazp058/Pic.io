/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author Franklin
 */
public class MainClass extends Application {

    public static String loginName = "login";
    public static String mainName = "main";
    
    public void start(Stage primaryStage){
        
        ScreensController myController = new ScreensController();

        ControllableScene loginScene = new LoginRegisterScene();
        ControllableScene mainScene = new MainScene();
        
        myController.setSize(new Integer[]{1280,720});
        
        myController.addScene(this.loginName, loginScene);
        myController.addScene(this.mainName, mainScene);
        myController.setScene(mainName);
                
        Scene scene = myController.getScene();
        
        primaryStage.setTitle("A Fox's Gallery");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
