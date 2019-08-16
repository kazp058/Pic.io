/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolkit;

import clases.Album;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author KEVIN Z
 */
public class Importer {

    public static Album importarSave() {

        ObjectInputStream entrada;
        try {
            entrada = new ObjectInputStream(new FileInputStream("C:/Users/KEVIN Z/Documents/NetBeansProjects/proyectofinal/src/dataSave/save.txt"));
            Album album = (Album) entrada.readObject();
            
            return album;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Importer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
