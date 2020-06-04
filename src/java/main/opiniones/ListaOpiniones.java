/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.opiniones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.sql.DataSource;
import main.Exception.UserNotExistException;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class ListaOpiniones {
    
    private static final String NOMBRE_TABLA = "OPINIONES";
    
    private DataSource dataSource;
    private ArrayList<Opinion> opiniones;
    
    public ListaOpiniones(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public ArrayList<Opinion> getOpinones() throws SQLException{
        
        this.opiniones = new ArrayList<>();
        String sentenciaSQL = "select * from " + NOMBRE_TABLA;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {

                        int id = Integer.parseInt(resultSet.getString("ID"));
                        String description = resultSet.getString("DESCRIPCION");
                        int puntuacion = Integer.parseInt(resultSet.getString("PUNTUACION"));
                        int codProducto = Integer.parseInt(resultSet.getString("COD_PRODUCTO"));
                        String nickname = resultSet.getString("NICKNAME");

                        opiniones.add((new Opinion(id, description, puntuacion, codProducto, nickname)));
                    }
                }
            }
        }
        return opiniones;   
    }
    
    public List<Opinion> getOpionesProducto(int codigo) throws SQLException {
        Stream<Opinion> opionesProducto = getOpinones().stream()
                .filter(o -> o.getCodProducto() == codigo);
        return opionesProducto.collect(Collectors.toList());

    }
    
    public void mete(Opinion opinion) throws SQLException {

        String sentenciaSQL = "INSERT INTO " + NOMBRE_TABLA + " VALUES('"
                + opinion.getId() + "', '"
                + opinion.getDescription() + "', '"
                + opinion.getPuntuacion() + "', '"
                + opinion.getCodProducto()+ "', '"
                + opinion.getNickName() + "')";

        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(sentenciaSQL);
            }
        }
    }
    
    public int getCodigoSiguiente() throws SQLException{
        
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
