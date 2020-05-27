/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.productos;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Producto {
    
    private int cod;
    private int precio;
    private String nombre;
    private String pathRuta;

    public Producto(int cod, int precio, String nombre, String pathRuta) {
        this.cod = cod;
        this.precio = precio;
        this.nombre = nombre;
        this.pathRuta = pathRuta;
    }

    public int getCod() {
        return cod;
    }

    public int getPrecio() {
        return precio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPathRuta() {
        return pathRuta;
    }
}
