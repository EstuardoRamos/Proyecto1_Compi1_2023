package com.example.game1.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.example.game1.DrawWorld
import com.example.game1.MainActivity
import com.example.game1.analisis.CounterLexer
import com.example.game1.analisis.parser
import java.io.StringReader

class Manejador {
    fun leer(xml: String){
        val lexer = CounterLexer(StringReader(xml))
        val parser = parser(lexer)
        //parser.parse()
        println("Ser parseo")
        val world=parser.parse().value as World;

    }
}