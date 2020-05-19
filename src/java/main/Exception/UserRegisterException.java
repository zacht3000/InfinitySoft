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
public class UserRegisterException extends Exception {
    
    String texto;
    
    public UserRegisterException(String texto) {
        super();
        this.texto = texto;
    }

    @Override
    public String getMessage() {
        return "Introduce tu " + texto + "."; //To change body of generated methods, choose Tools | Templates.
    }
    
}
