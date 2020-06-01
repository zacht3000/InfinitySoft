/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.productos;

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
public class ListaProductos {
    private static final String NOMBRE_TABLA = "PRODUCTO";
    
    private DataSource dataSource;
    private ArrayList<Producto> productos;
    
    public ListaProductos(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ArrayList<Producto> getProductos() throws SQLException{
        
        this.productos = new ArrayList<>();
        String sentenciaSQL = "select * from " + NOMBRE_TABLA;
        try (Connection connection = this.dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sentenciaSQL)) {

                    while (resultSet.next()) {

                        int cod = Integer.parseInt(resultSet.getString("COD"));
                        int precio = Integer.parseInt(resultSet.getString("PRECIO"));
                        String nombre = resultSet.getString("NOMBRE");
                        String pathRuta = resultSet.getString("PATH_RUTA");
                        productos.add((new Producto(cod, precio, nombre, pathRuta)));
                    }
                }
            }
        }
        return productos;   
    }
    
}
