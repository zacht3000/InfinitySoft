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
public class ContrasenyaIncorrectaException extends Exception {
    
    public ContrasenyaIncorrectaException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Contraseña incorrecta pruebe de nuevo.";
    }
}
