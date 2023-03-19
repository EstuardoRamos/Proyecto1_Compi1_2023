package com.example.game1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import com.example.game1.analisis.CounterLexer
import com.example.game1.analisis.parser
import com.example.game1.game.Casilla
import com.example.game1.game.World
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader

class MainActivity : AppCompatActivity() {
   // var world1=World();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtén referencias a los EditText y Button en tu archivo XML
        val editTextRows = findViewById<EditText>(R.id.editTextRows)
        val editTextColumns = findViewById<EditText>(R.id.editTextColumns)
        val buttonCreateTable = findViewById<Button>(R.id.buttonCreateTable)
       // val world = intent.getSerializableExtra("world") as World
        val input = intent.getStringExtra("input")
        var objetoEnviado = intent.extras
        println("----------------hola mundo desde otra ventana--------------")
        val lexer = CounterLexer(StringReader(input))
        val parser = parser(lexer)
        val world=parser.parse().value as World;
       if (parser.errores.size >0){
           if (input != null) {
               crearTableErrors(input)
           }
       }else{
           println(input)
           drawWorld(world)
       }


        // Agrega un listener para el botón
        buttonCreateTable.setOnClickListener {
            // Obtén los valores ingresados por el usuario

        }
    }

    fun drawWorld(w: World){
        // Crea una instancia de TableLayout y agrega filas y celdas
        val tableLayout = TableLayout(this)
        tableLayout.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )

        val casillas1= Array(w.filas){Array<Casilla>(w.columnas){ Casilla(1,1, "player","n")}}
        var undefined_casilla:Casilla;
        for (i in (0 until w.filas)){
            for (j in (0 until w.columnas)){
                for (board in w.boards) {
                    //2 , 1
                    if (board.posx==j && board.posy==i){
                        //i=1  j=2
                        casillas1[i][j]=board;
                    }

                }
                for (board in w.boxes) {
                    if (board.posx==j && board.posy==i){
                        casillas1[i][j]=board;
                    }
                }
                for (board in w.targets) {
                    if (board.posx==j && board.posy==i){
                        casillas1[i][j]=board;
                    }/*else{
                            casillas1[i][j]=Casilla(i,j,"underined", "n")
                        }*/
                }
                if (w.player.posx==j && w.player.posy==i){
                    casillas1[i][j]=w.player;
                }

            }

        }



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
                    }else if (casillas1[i][j].tipo.equals("player")) {
                        Color.CYAN
                    }else{
                        Color.BLACK
                    }
                )
                tableRow.addView(textView)
            }

            tableLayout.addView(tableRow)
        }

        // Agrega la tabla a tu diseño
        findViewById<ViewGroup>(R.id.container).addView(tableLayout)
    }
    fun crearTableErrors(xmlString: String){

        val tableLayout = findViewById<TableLayout>(R.id.table_layout)

        val factory = XmlPullParserFactory.newInstance()
        factory.isNamespaceAware = true
        val xpp = factory.newPullParser()
        xpp.setInput(xmlString.reader())

        var eventType = xpp.eventType
        var currentTag: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    currentTag = xpp.name
                    if (currentTag == "error") {
                        val tableRow = TableRow(this)

                        val valueTextView = TextView(this)
                        val typeTextView = TextView(this)
                        val lineTextView = TextView(this)
                        val columnTextView = TextView(this)

                        valueTextView.text = xpp.getAttributeValue(null, "value")
                        typeTextView.text = xpp.getAttributeValue(null, "type")
                        lineTextView.text = xpp.getAttributeValue(null, "line")
                        columnTextView.text = xpp.getAttributeValue(null, "column")

                        tableRow.addView(valueTextView)
                        tableRow.addView(typeTextView)
                        tableRow.addView(lineTextView)
                        tableRow.addView(columnTextView)

                        tableLayout.addView(tableRow)
                    }
                }
            }
            findViewById<ViewGroup>(R.id.container).addView(tableLayout)
        }     //eventType = xpp.next()
    }
}