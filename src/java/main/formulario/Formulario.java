/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.formulario;

import main.usuarios.TipoUsuario;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Formulario {
    
    private int id;
    private String nickname;
    private String nombre;
    private String apellidos;
    private String correo;
    private String respuesta;
    
    public Formulario(int id, String nickname, String nombre, 
            String apellidos, String correo, String respuesta){
        this.id = id;
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.respuesta = respuesta;
    }

    public int getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRespuesta() {
        return respuesta;
    }
   
    
}
