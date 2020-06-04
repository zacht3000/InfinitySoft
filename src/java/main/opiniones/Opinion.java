/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.opiniones;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class Opinion {
    private int id;
    private String description;
    private int puntuacion;
    private int codProducto;
    private String nickname;

    public Opinion(int id, String description, int puntuacion, int codProducto, String nickname) {
        this.id = id;
        this.description = description;
        this.puntuacion = puntuacion;
        this.codProducto = codProducto;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public String getNickName() {
        return nickname;
    }
   
    
}
