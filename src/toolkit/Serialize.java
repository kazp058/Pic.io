/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolkit;

import clases.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Franklin
 */
public class Serialize {
    
    public ArrayList<Object> importar(){
        /* Este método va a retornarnar un ArrayLis: el primer valor será un 
        bool que muestra si se pudo completar la acción, el segundo es el
        mensaje de error si es que hay uno y el tercero unhashmap de todos los
        usuarios*/
        ArrayList<Object> resultado = new ArrayList<>();
        Boolean valor=false;
        String mensaje = null;
        HashMap<String,User> lista=new HashMap<>();
        try(FileInputStream filein=new FileInputStream("src/Data/user.ser");
                ObjectInputStream in= new ObjectInputStream(filein)){
            lista= (HashMap<String,User>) in.readObject();
            valor=true;
        } catch (FileNotFoundException | ClassNotFoundException e){
            mensaje=e.toString();
        } catch(IOException e){
            mensaje=e.toString();
        } finally{
            resultado.add(valor);
            resultado.add(mensaje);
            resultado.add(lista);
            return resultado;
        }
    }
    
    public ArrayList<Object> iniciarSesion(User user) {
        /* Retorna un ArrayList donde el primer valor retorna un bool que indica si se pudo completar la acción y el
        otro es el usuario*/
       ArrayList<Object> resultado=new ArrayList<>();
       ArrayList importacion=this.importar();
       HashMap<String,User> usuarios=(HashMap<String,User>) importacion.get(2);
       if (usuarios.containsKey(user.getUsername())){
           resultado.add(true);
           resultado.add(usuarios.get(user.getUsername()));
       } else {
           resultado.add(false);
       }
       return resultado;
    }
    public boolean actualizarUsuario(User user){
        ArrayList importacion=this.importar();
        HashMap<String,User> usuarios=(HashMap<String,User>) importacion.get(2);
        
        try(FileOutputStream fileout= new FileOutputStream("src/Data/user.ser");
                ObjectOutputStream out= new ObjectOutputStream(fileout)){
            if (usuarios.containsKey(user.getUsername())){
                usuarios.put(user.getUsername(), user);
                out.writeObject(usuarios);
                return true;
            } else{
                return false;
            }
        } catch (FileNotFoundException e){
            return false;
        } catch (IOException e){
            return false;
        }
    }
    
   public ArrayList<Object> crearUsuario(User user){
       /* Este método retorna un ArrayList: el primer valor será un bool que
       muestra si se pudo hacer la acción y el segundo retorna un mensaje
       de error o de creación exitosa*/
       ArrayList<Object> resultado=new ArrayList<>();
       ArrayList importacion=this.importar();
       HashMap<String,User> usuarios=(HashMap<String,User>) importacion.get(2);
        
       try(FileOutputStream fileout= new FileOutputStream("src/Data/user.ser");
                ObjectOutputStream out= new ObjectOutputStream(fileout)){
            if (usuarios.containsKey(user.getUsername())){
                out.writeObject(usuarios);
                resultado.add(false);
                resultado.add("Un usuario con ese username ya existe.");
                return resultado;
            } else{
                usuarios.put(user.getUsername(), user);
                out.writeObject(usuarios);
                resultado.add(true);
                resultado.add("Usuario creado exitosamente");
                return resultado;
            }
        } catch (FileNotFoundException e){
            resultado.add(true);
            resultado.add("Usuario creado exitosamente");
            return resultado;
        } catch (IOException e){
            resultado.add(false);
            resultado.add(e.toString());
            return resultado;
        }
       
    }
}
