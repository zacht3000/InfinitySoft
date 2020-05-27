/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.usuarios;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Usuario {
    
    public static final String PATRON_CORREO = "^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PATRON_CONTRASENYA = "[0-9]{4}";
    public static final String PATRON_NICKNAME= "[A-Za-z]{4,6}";

    private String nickName;
    private String correo;
    private String nombre;
    private String apellidos;
    private String contrasenya;
    private TipoUsuario tipo;
    
    public Usuario(String nickName, String correo, String nombre, 
            String apellidos, String contrasenya, TipoUsuario tipo){
        this.nickName = nickName;
        this.correo = correo;
        this.nombre = nombre;
        this.apellidos =  apellidos;
        this.contrasenya =  contrasenya;
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

    public String getContrasenya() {
        return contrasenya;
    }
    
    public TipoUsuario getTipo() {
        return tipo;
    }
    
    public boolean esContrasenyaCorecta(String contrasenya){
            return this.getContrasenya().equals(contrasenya);
    }
    
    @Override
    public String toString() {
        return getNickName() + " " + getNombre(); //To change body of generated methods, choose Tools | Templates.
    }  
    
}
