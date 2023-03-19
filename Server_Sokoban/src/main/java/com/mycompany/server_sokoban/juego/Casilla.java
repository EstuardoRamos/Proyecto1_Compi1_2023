/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.juego;

import com.mycompany.server_sokoban.parser.Token;

/**
 * 
 * @author Estuardo Ramos
 */

public class Casilla {
    private int posx;
    private int posy;
    private String tipo;
    private String color;

    public Casilla() {
    }

    public Casilla(int posx, int posy, String tipo, String color) {
        this.posx = posx;
        this.posy = posy;
        this.tipo = tipo;
        this.color = color;
    }

    public Casilla(int posx, int posy, String tipo) {
        this.posx = posx;
        this.posy = posy;
        this.tipo = tipo;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void infoCasilla(){
        Casilla[][] casillas= new Casilla[4][4];
        System.out.println("Type= "+tipo+"- PosX= "+posx+"- Posy= "+posy);
    }
    
    

}
