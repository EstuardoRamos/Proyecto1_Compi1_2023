package com.example.game1

import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.game1.analisis.CounterLexer
import com.example.game1.analisis.parser
import com.example.game1.game.Casilla
import com.example.game1.game.World
import java.io.StringReader

class DrawWorld : AppCompatActivity() {

    var input: String = ""

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_world)


        //val world = intent.getSerializableExtra("world") as World
        val world = intent.getSerializableExtra("world") as World
        //val input1 = intent.getStringExtra("input")
            Log.println(Log.INFO, TAG, "Compiling")
            println("hola pintando desde la otra ventana")
            //val lexer = CounterLexer(StringReader(entrada))
           // val parser = parser(lexer)
            //parser.parse();
            dibujarWorld(world)
            println("hola pintando desde la otra ventana---------------------")


    }

    fun dibujarWorld( w: World){
        val tableLayout = TableLayout(this)
        tableLayout.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT

        )
        val casillas1= Array(w.filas){Array<Casilla>(w.columnas){ Casilla(1,1, "player","n") }}
        var undefined_casilla: Casilla;
        for (i in (0 until w.filas)){
            for (j in (0 until w.columnas)){
                for (board in w.boards) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }

                }
                for (board in w.boxes) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }
                }
                for (board in w.targets) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }/*else{
                            casillas1[i][j]=Casilla(i,j,"underined", "n")
                        }*/
                }
            }

        }


        //var world = World();
        for (i in 0 until w.filas) {
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            for (j in 0 until w.columnas) {
                val textView = TextView(this)
                textView.text = "Celda $i,$j"
                textView.setBackgroundColor(
                    if (casillas1[i][j].tipo.equals("BRICK")) {
                        Color.GRAY
                    } else if (casillas1[i][j].tipo.equals("HALL")) {
                        Color.BLUE
                    }else if (casillas1[i][j].tipo.equals("targets")) {
                        Color.GREEN
                    }else if (casillas1[i][j].tipo.equals("boxes")) {
                        Color.YELLOW
                    }else{
                        Color.WHITE
                    }
                )
                tableRow.addView(textView)
            }

            tableLayout.addView(tableRow)
        }

        // Agrega la tabla a tu diseño
        findViewById<ViewGroup>(R.id.container).addView(tableLayout)
    }
}