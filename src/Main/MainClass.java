/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Scenes.*;
import clases.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import toolkit.Serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 *
 * @author Franklin
 */
public class MainClass extends Application {

    Font bits;
    Background background;
    public static String loginName = "login";
    public static String mainName = "main";
    public static String registerName = "register";
    public Stage stage;

    public MainClass() throws FileNotFoundException {
        this.bits = Font.loadFont(new FileInputStream("src/Data/fonts/upheavtt.ttf"), 20);
        File dir = new File("src/Data/gifs");
        File[] files = dir.listFiles();
        Random rand = new Random();
        File location = files[rand.nextInt(files.length)];
        BackgroundImage bgi;
        Image image = new Image(new FileInputStream(location));
        bgi = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.background = new Background(bgi);

    }

    public void start(Stage primaryStage) {

        this.stage = primaryStage;

        ScreensController myController = new ScreensController(primaryStage);

        ControllableScene mainScene = new MainScene();
        ControllableScene loginScene = new LoginScene(bits, background);
        ControllableScene registerScene = new LoginRegisterScene(bits, background);

        myController.setSize(new Integer[]{1280, 720});

        myController.addScene(loginName, loginScene);
        myController.addScene(mainName, mainScene);
        myController.addScene(registerName, registerScene);
        myController.setScene(loginName);

        Scene scene = myController.getScene();

        primaryStage.setTitle("A Fox's Gallery");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((e) -> {
            if (!(myController.getCurrentUser().getName().equals(""))) {
                User user = myController.getCurrentUser();
                Serialize tools = new Serialize();
                boolean x = tools.actualizarUsuario(user);
            }
        });
    }

    /**ad
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
