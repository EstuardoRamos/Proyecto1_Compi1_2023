/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.comunication;

import com.mycompany.server_sokoban.parser.CounterLexer;
import com.mycompany.server_sokoban.parser.parser;
import java.io.Reader;
import java.io.StringReader;

/**
 * 
 * @author Estuardo Ramos
 */
public class Manejador {
    
    
    public static String leer(String json){
        String xml="";
            Reader reader = new StringReader(json);
            CounterLexer counterLexer = new CounterLexer(reader);
            parser parser = new parser(counterLexer);
            try {
                parser.parse();
                if(parser.getErrores().size()>0){
                    System.out.println("si tiene errores ");
                    xml=parser.getWorld();
                }else{
                 xml= parser.getWorld();
                    System.out.println("hola");
                    System.out.println(parser.getWorld());
                }
                
                System.out.println(parser.getErrores().size());
            } catch (Exception e) {
                e.printStackTrace();
                // Aqui ustedes manejan de forma correcta el error.
            }
            return xml;
        
    }

}
