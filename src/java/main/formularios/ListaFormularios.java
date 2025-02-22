/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.formularios;

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
public class ListaFormularios {
    
    private static final String NOMBRE_TABLA = "FORMULARIO";
    private ArrayList<Formulario> formularios;
    private DataSource dataSource;
    
    public ListaFormularios(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Formulario> getFormularios() throws SQLException{
        
        this.formularios = new ArrayList<>();
        String sentenciaSQL = "SELECT * FROM " + NOMBRE_TABLA;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String nickname = resultSet.getString("nickname");
                        String nombre = resultSet.getString("nombre");
                        String apellidos = resultSet.getString("apellidos");
                        String correo = resultSet.getString("correo");
                        String area = resultSet.getString("area");
                        String pregunta = resultSet.getString("pregunta");
                        

                        formularios.add((new Formulario(id, nickname, nombre, apellidos, correo, area,pregunta)));
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
                + formulario.getArea() + "', '"
                + formulario.getPregunta() + "')";

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
