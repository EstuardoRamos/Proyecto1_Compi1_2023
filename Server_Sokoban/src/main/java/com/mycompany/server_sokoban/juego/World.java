/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.juego;

import com.mycompany.server_sokoban.juego.Casilla;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * 
 * @author Estuardo Ramos
 */
public class World {
    
    private String name;
    private int filas;
    private int columnas;
    private Config config;
    private ArrayList<Casilla> boards;
    private ArrayList<Casilla> boxes;
    private ArrayList<Casilla> targets;
    private Casilla player;

    public World(String name, int filas, int columnas, Config config, ArrayList<Casilla> boards, ArrayList<Casilla> boxes, ArrayList<Casilla> targets, Casilla player) {
        this.name = name;
        this.filas = filas;
        this.columnas = columnas;
        this.config = config;
        this.boards = boards;
        this.boxes = boxes;
        this.targets = targets;
        this.player = player;
    }

    public World(String name, int filas, int columnas, ArrayList<Casilla> boards, ArrayList<Casilla> boxes, ArrayList<Casilla> targets, Casilla player) {
        this.name = name;
        this.filas = filas;
        this.columnas = columnas;
        this.boards = boards;
        this.boxes = boxes;
        this.targets = targets;
        this.player = player;
    }
    

    public World() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ArrayList<Casilla> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Casilla> boards) {
        this.boards = boards;
    }

    public ArrayList<Casilla> getBoxes() {
        return boxes;
    }

    public void setBoxes(ArrayList<Casilla> boxes) {
        this.boxes = boxes;
    }

    public ArrayList<Casilla> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Casilla> targets) {
        this.targets = targets;
    }

    public Casilla getPlayer() {
        return player;
    }

    public void setPlayer(Casilla player) {
        this.player = player;
    }
    
    
    public void agregarCasilla(){
       
    }
    
    public void dibujarTablero(){
        //arreglo casillas
    }
    
    public void escribirJuego(){
        System.out.println("World ---->"+name);
        System.out.println("Filas ---->"+filas);
        System.out.println("Columnas-->"+columnas);
        for (Casilla board : boards) {
            board.infoCasilla();
        }
        for (Casilla boxe : boxes) {
            boxe.infoCasilla();
        }
        for (Casilla target : targets) {
            target.infoCasilla();
        }
    }
    
    public String convertirXML(){
        String xml;
        
        String nameXml="<name>"+name+"</name>";
        String rowsXml="\t\t<rows>"+filas+"</rows>";
        String columsXml="\t\t<cols>"+columnas+"</cols>";
        xml=nameXml+"\n";
        xml+= rowsXml+"\n"+columsXml+"\n";
        for (Casilla board : boards) {
           xml+="\t\t<board>"+"\n\t\t\t<posX>"+board.getPosx()+"</posX>"+"\n\t\t\t<posY>"+board.getPosy()+"</posY>"+"\n\t\t\t<type>"+board.getTipo()+"</type>"+"\n\t\t</board>\n";
        }
        for (Casilla board : boxes) {
           xml+="\t\t<boxes>"+"\n\t\t\t<posX>"+board.getPosx()+"</posX>"+"\n\t\t\t<posY>"+board.getPosy()+"</posY>"+"\n\t\t</boxes>\n";
        }
        for (Casilla board : targets) {
           xml+="\t\t<targets>"+"\n\t\t\t<posX>"+board.getPosx()+"</posX>"+"\n\t\t\t<posY>"+board.getPosy()+"</posY>"+"\n\t\t</targets>\n";
        }
        xml+="\t\t<player>"+"\n\t\t\t<posX>"+player.getPosx()+"</posX>"+"\n\t\t\t<posY>"+player.getPosy()+"</posY>"+"\n\t\t</player>\n";
        String xml1="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "\t\t<world>\n"
                +"\t"+xml
                + "</world>";
        System.out.println(xml1);
        guardarXML(xml1);
        mostrarXmlMundo(xml1);
        return xml1;
    }
    
    
    public String guardarXML(String xml){
        //Creamos un arreglo que contenga nombre y un string
        ArrayList<String> xmls= new ArrayList<>();
        for (String xml1 : xmls) {
            if(xml != xml1){
                xmls.add(xml);
            }
        }
        //nombre sera el nombre del mundo y el string seran las config del juego
        return "xmls";
    }
    
    public void mostrarXmlMundo(String xml){

            JFrame frame = new JFrame("World");
            JTextArea textArea = new JTextArea(xml);
            JScrollPane scrollPane = new JScrollPane(textArea);
            frame.add(scrollPane);
            frame.setSize(400, 400);
            frame.setVisible(true);
    }
}
