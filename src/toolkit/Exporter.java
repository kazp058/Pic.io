/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolkit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KEVIN Z
 * @param
 */
public class Exporter {

    public static void guardarObjeto(Object objeto) {
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("C:/Users/KEVIN Z/Documents/NetBeansProjects/proyectofinal/src/dataSave/save.txt"));
            salida.writeObject(objeto);
            salida.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Exporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
