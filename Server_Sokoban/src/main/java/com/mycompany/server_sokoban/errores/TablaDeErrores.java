/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.errores;


import com.mycompany.server_sokoban.parser.Token;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import java.util.ArrayList;

/**
 * 
 * @author Estuardo Ramos
 */
public class TablaDeErrores {
    private ArrayList<Error> listaErroes;
    
    
    
    public void agregarError(String lexema, int linea, int columna, String tipo, String descripsion){
        //Error error = new Error(lexema, linea, columna, tipo, descripsion); 
       // listaErroes.add(error);
        
    }

    public TablaDeErrores() {
    }
    
    public String mostrarErrores(ArrayList<Token> tokenError) {
        //ArrayList<Token> tokenError = new ArrayList<>();
        
        String xml="";
        int numErrors = tokenError.size();

        if (numErrors > 1) {
            Element root = new Element("errors");

            for (Token t : tokenError) {
                Element error = new Element("error");
                error.addContent(new Element("value").setText(t.getValue()));
                error.addContent(new Element("type").setText(Integer.toString(t.getType())));
                error.addContent(new Element("line").setText(Integer.toString(t.getLine())));
                error.addContent(new Element("column").setText(Integer.toString(t.getColumna())));
                root.addContent(error);
            }

            Document doc = new Document(root);

            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            String xmlString = xmlOutput.outputString(doc);
            xml=xmlString;
            JFrame frame = new JFrame("Errors");
            JTextArea textArea = new JTextArea(xmlString);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane);
            frame.setSize(400, 400);
            frame.setVisible(true);
        }
        return xml;
    }
}


