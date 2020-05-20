/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.formulario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class ListaFormulario {
    
    private static final String NOMBRE_TABLA = "FORMULARIO";
    private ArrayList<Formulario> formularios;
    private DataSource dataSource;
    
    public ListaFormulario(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Formulario> getUsuarios() throws SQLException{
        
        this.formularios = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + NOMBRE_TABLA;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nombre = resultSet.getString("nombre");
                        String apellidos = resultSet.getString("apellidos");
                        String correo = resultSet.getString("correo");
                        String respuesta = resultSet.getString("area");
                        String nickname = resultSet.getString("nickname");

                        formularios.add((new Formulario(id, nickname, nombre, apellidos, correo, respuesta)));
                    }
                }
            }
        }
        return formularios;   
    }
    
    public void mete(Formulario formulario) throws SQLException {

        String sentenciaSQL = "INSERT INTO " + NOMBRE_TABLA + " VALUES('"
                + formulario.getId() + "', '"
                + formulario.getNickname() + "', '"
                + formulario.getNombre() + "', '"
                + formulario.getApellidos() + "', '"
                + formulario.getCorreo() + "', '"
                + formulario.getRespuesta() + "')";

        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sentenciaSQL);
            }
        }
    }
    
    public int getIdSiguiente() throws SQLException{
        
        String sentenciaSQL = "SELECT MAX(ID) AS CODIGO FROM " + NOMBRE_TABLA;
        int id = 0;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {

                        id = resultSet.getInt("CODIGO");
                        return id + 1;
                    }
                }
            }
        }
        
        return id;
    }

}
