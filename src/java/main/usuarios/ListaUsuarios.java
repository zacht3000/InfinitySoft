/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.usuarios;

import com.sun.javafx.image.impl.ByteArgb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import javax.sql.DataSource;
import main.Exception.UserNotExistException;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class ListaUsuarios {
    
    private static final String NOMBRE_TABLA = "USUARIO";
    private static final String NOMBRE_COLUMNA_NICKNAME = "NICKNAME";
    private static final String NOMBRE_COLUMNA_CORREO = "CORREO";
    private static final String NOMBRE_COLUMNA_NOMBRE = "NOMBRE";
    private static final String NOMBRE_COLUMNA_APELLIDOS = "APELLIDOS";
    private static final String NOMBRE_COLUMNA_CONTRASENYA = "CONTRASENYA";
    private static final String NOMBRE_COLUMNA_TIPO = "TIPO";
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
                        String correo = resultSet.getString("correo");
                        String nombre = resultSet.getString("nombre");
                        String apellidos = resultSet.getString("apellidos");
                        String contraseña = resultSet.getString("contrasenya");
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
    
    public boolean yaExisteNickNameOrCorreo(String param) throws SQLException{
        for (Usuario usuario : this.getUsuarios()) {
            if (usuario.getNickName().equals(param) || usuario.getCorreo().equals(param)) {
                return true;
            }
        }
        return false;
    }
    
    public Usuario getBuscaUsuarioNickNameOrCorreo(String param) throws SQLException, UserNotExistException{
        Optional<Usuario> usuario = getUsuarios().stream()
                .filter(u -> param.equals(u.getNickName()) || param.equals(u.getCorreo())).findFirst();
        
        if (usuario.isPresent()) {
            return usuario.get();
        }
        
        throw new UserNotExistException();
    }
    
    public boolean listaUsuarioNoAdminAnonimo() throws SQLException{
        return getUsuarios().size() <= 2;
    }

    public void modificarUsuario(Usuario usuario) throws SQLException {
        String sentenciaSQL = "UPDATE " + NOMBRE_TABLA + " SET " 
                + NOMBRE_COLUMNA_NICKNAME + " = '"
                + usuario.getNickName() + "', "
                + NOMBRE_COLUMNA_CORREO + " = '"
                + usuario.getCorreo() + "', "
                + NOMBRE_COLUMNA_NOMBRE + " = '"
                + usuario.getNombre() + "', "
                + NOMBRE_COLUMNA_APELLIDOS + " = '"
                + usuario.getApellidos() + "', "
                + NOMBRE_COLUMNA_CONTRASENYA+ " = '"
                + usuario.getContrasenya() + "', "
                + NOMBRE_COLUMNA_TIPO + " = '"
                + usuario.getTipo().toString() + "' WHERE " 
                + NOMBRE_COLUMNA_NICKNAME + " = '"
                + usuario.getNickName() + "'";

        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sentenciaSQL);
            }
        }
    }

    
}
