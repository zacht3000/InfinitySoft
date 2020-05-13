/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios;

import com.sun.javafx.image.impl.ByteArgb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class ListaUsuarios {
    
    private static final String NOMBRE_TABLA = "USUARIO";
    private DataSource dataSource;
    private ArrayList<Usuario> usuarios;
    
    public ListaUsuarios(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Usuario> getUsuarios() throws SQLException{
        
        this.usuarios = new ArrayList<>();
        String sentenciaSQL = "select * from " + NOMBRE_TABLA;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {

                        String nickName = resultSet.getString("nickname");
                        String correo = resultSet.getString("correo");;
                        String nombre = resultSet.getString("nombre");;
                        String apellidos = resultSet.getString("apellidos");;
                        String contraseña = resultSet.getString("contrasenya");;
                        TipoUsuario tipo = TipoUsuario.tipoSegunTexto(resultSet.getString("tipo"));

                        usuarios.add((new Usuario(nickName, correo, nombre, apellidos, contraseña, tipo)));
                    }
                }
            }
        }


        return usuarios;   
    }
    
    public void mete(Usuario usuario) throws SQLException {

        String sentenciaSQL = "INSERT INTO " + NOMBRE_TABLA + " VALUES('"
                + usuario.getNickName() + "', '"
                + usuario.getCorreo() + "', '"
                + usuario.getNombre() + "', '"
                + usuario.getApellidos() + "', '"
                + usuario.getContrasenya() + "', '"
                + usuario.getTipo().toString() + "')";

        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sentenciaSQL);
            }
        }
    }
    
    public boolean yaExisteNickName(String nickName) throws SQLException{
        for (Usuario usuario : this.getUsuarios()) {
            if (usuario.getNickName().equals(nickName)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean yaExisteCorreo(String correo) throws SQLException{
        for (Usuario usuario : this.getUsuarios()) {
            if (usuario.getCorreo().equals(correo)) {
                return true;
            }
        }
        
        return false;
    }
    
    public Usuario getBuscaUsuarioNickName(String nickName) throws SQLException{
        for (Usuario usuario : this.getUsuarios()) {
            if(usuario.getNickName().endsWith(nickName))
                return usuario;
        }
        return null;
    }
    
    public Usuario getBuscaUsuarioCorreo(String correo) throws SQLException{
        for (Usuario usuario : this.getUsuarios()) {
            if(usuario.getCorreo().endsWith(correo))
                return usuario;
        }
        return null;
    }
    
    public boolean esContrasenyaCorecta(Usuario usuario, String contrasenya){
            return usuario.getContrasenya().equals(contrasenya);
    }
   
    
}
