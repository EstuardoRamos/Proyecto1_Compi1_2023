/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.errores;

import com.mycompany.server_sokoban.parser.Token;

/**
 * 
 * @author Estuardo Ramos
 */
public class Error {
    Token tokenError;
    String tipo;
    String descripcion;

    public Error() {
    }

    public Error(Token tokenError, String tipo, String descripcion) {
        this.tokenError = tokenError;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public Token getTokenError() {
        return tokenError;
    }

    public void setTokenError(Token tokenError) {
        this.tokenError = tokenError;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  
    

}
