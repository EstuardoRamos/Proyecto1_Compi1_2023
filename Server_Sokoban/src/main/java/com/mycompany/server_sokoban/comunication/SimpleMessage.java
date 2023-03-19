/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.comunication;

/**
 * 
 * @author Estuardo Ramos
 */
import java.io.Serializable;

public class SimpleMessage implements Serializable {

    private String message;

    private static final long serialVersionUID = 6529685098267757690L;

    public SimpleMessage() {
    }

    public SimpleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SimpleMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
