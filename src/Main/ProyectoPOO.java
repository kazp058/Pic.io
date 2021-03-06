/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import clases.User;
import java.util.Arrays;
import javafx.scene.control.Alert;

/**
 *
 * @author Franklin
 */
public class ProyectoPOO extends Application {
    Font bits;
    Background background;
    Image logo;

    public ProyectoPOO() throws FileNotFoundException {
        this.bits = Font.loadFont(new FileInputStream("src/Data/fonts/upheavtt.ttf"),20);
        File dir= new File("src/Data/gifs");
        File[] files=dir.listFiles();
        Random rand=new Random();
        File location=files[rand.nextInt(files.length)];
        BackgroundImage bgi;
        Image image=new Image(new FileInputStream(location));
        bgi = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));
        this.background=new Background(bgi);
        this.logo=new Image(new FileInputStream("src/Data/pics/cup.png"));
    }
    private Text texto(String texto){
        Text text= new Text(texto);
        text.setFont(this.bits); text.setFill(Color.WHITE);
        text.setStrokeType(StrokeType.OUTSIDE);
        text.setStroke(Color.BLACK);text.setStrokeWidth(1);
        return text;
    } 
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        /* Inicializaci??n de botonoes, textboxs y labels */
        Button iniciar= new Button("Iniciar sesi??n");
        Button crear= new Button("Crear un nuevo usuario");
        TextField username= new TextField();
        TextField password= new TextField(); 
        Label un=new Label();
        Label pw=new Label(); 
        /* Modificaci??n  de los nodos*/
        un.setGraphic(this.texto("Username: "));
        pw.setGraphic(this.texto("Contrase??a: "));
        iniciar.setFont(this.bits); crear.setFont(this.bits);
        un.setMinSize(100,30);
        pw.setMinSize(100, 30);
        username.setFont(this.bits);
        username.setMaxWidth(500);
        password.setFont(this.bits);
        password.setMaxWidth(500);
        VBox boxmaster=new VBox(); /* Caja maestra*/
        boxmaster.setSpacing(15.0);
        boxmaster.setAlignment(Pos.CENTER);
        HBox box= new HBox(); /*Caja que une las labels con los cuadros dde texto */
        box.setAlignment(Pos.CENTER);
        box.setSpacing(5.0);
        VBox labels= new VBox(); /* Caja de labels*/
        labels.setSpacing(10.0);
        labels.setAlignment(Pos.CENTER_LEFT);
        labels.getChildren().add(un); labels.getChildren().add(pw);
        VBox textboxs=new VBox(); /*Caja de los cuadros de texto */
        textboxs.setAlignment(Pos.CENTER_RIGHT);
        textboxs.setSpacing(10.0);
        textboxs.getChildren().add(username); textboxs.getChildren().add(password);
        box.getChildren().add(labels);box.getChildren().add(textboxs);
        HBox botones=new HBox();
        botones.setSpacing(15.0);
        botones.setAlignment(Pos.BASELINE_CENTER);
        botones.getChildren().add(iniciar); botones.getChildren().add(crear);
        ImageView logito= new ImageView(this.logo);
        logito.setFitHeight(200); logito.setFitWidth(200);
        /* A??adiendo los nodos a las diferentes cajas, escenas y stages*/
        boxmaster.getChildren().add(logito);
        boxmaster.getChildren().add(box);
        boxmaster.getChildren().add(botones);
        StackPane root= new StackPane(boxmaster);
        Scene scene = new Scene(root, 1280, 720);
        root.setBackground(this.background);
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
