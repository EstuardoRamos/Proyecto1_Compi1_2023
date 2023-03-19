/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.parser;

/**
 * 
 * @author Estuardo Ramos
 */
public class Token {
    private String value;
    private int type;
    private int line;
    private int columna;

    public Token(String value, int type, int line, int columna) {
        this.value = value;
        this.type = type;
        this.line = line;
        this.columna = columna;
    }

    public Token() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    
    
    
    
    

}
