package com.example.game1.game

import com.example.game1.analisis.CounterLexer
import com.example.game1.analisis.parser
import java.io.StringReader

class Respuestas {

    fun acciones(xmlRecibido: String){
        val lexer = CounterLexer(StringReader(xmlRecibido))
        val parser = parser(lexer)
        val world=parser.parse().value as World;
        /*val intent = Intent(this, DrawWorld::class.java).apply {
            putExtra("world_object", world) // Agrega el objeto al Intent
        }*/
    }
}