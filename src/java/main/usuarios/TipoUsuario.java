/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.usuarios;

import util.Intervalo;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public enum TipoUsuario {
    NORMAL, ADMINISTRADOR; 
    
     public static final TipoUsuario tipoSegunTexto(String tipo) {
        
         if(tipo.equals("NORMAL"))
             return NORMAL;
         else if(tipo.equals("ADMINISTRADOR"))
             return ADMINISTRADOR;
         else
             return null;
    }
}
