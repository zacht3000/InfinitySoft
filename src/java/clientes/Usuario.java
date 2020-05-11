/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientes;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Usuario {

    String nickName;
    String correo;
    String nombre;
    String apellidos;
    String contraseña;
    TipoUsuario tipo;
    
    public Usuario(String nickName, String correo, String nombre, 
            String apellidos, String contraseña, TipoUsuario tipo){
        this.nickName = nickName;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos =  apellidos;
        this.contraseña =  contraseña;
        this.tipo =  tipo;
    }
    
    public String getNickName() {
        return nickName;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }
    
}
