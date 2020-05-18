/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.Exception;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class UserNotExistException extends Exception {

       String texto;

    public UserNotExistException() {
        super();
        this.texto = texto;
    }

    @Override
    public String getMessage() {
        return "No exite el usuario o correo"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
