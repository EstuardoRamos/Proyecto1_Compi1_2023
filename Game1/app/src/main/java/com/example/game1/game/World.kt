package com.example.game1.game
import java.io.Serializable
class World(
    var name: String = "",
    var filas: Int = 0,
    var columnas: Int = 0,
    var config: Config? = null,
    var boards: ArrayList<Casilla> = arrayListOf(),
    var boxes: ArrayList<Casilla> = arrayListOf(),
    var targets: ArrayList<Casilla> = arrayListOf(),
    var player: Casilla
): Serializable {

    fun agregarCasilla() {

    }

    fun dibujarTablero() {
        //arreglo casillas
    }

    fun escribirJuego() {
        println("World ---->$name")
        println("Filas ---->$filas")
        println("Columnas-->$columnas")
        for (board in boards) {
            board.infoCasilla()
        }
        for (boxe in boxes) {
            boxe.infoCasilla()
        }
        for (target in targets) {
            target.infoCasilla()
        }
    }

    fun convertirXML() {
        var xml: String

        val nameXml = "<name>$name</name>"
        val rowsXml = "\t<rows>$filas</rows>"
        val columsXml = "\t<cols>$columnas</cols>"
        xml = "$nameXml\n"
        xml += "$rowsXml\n$columsXml\n"
        for (board in boards) {
            xml += "\t<board>\n\t\t<posX>${board.posx}</posX>\n\t\t<posY>${board.posy}</posY>\n\t\t<type>${board.tipo}</type>\n\t</board>\n"
        }
        for (board in boxes) {
            xml += "\t<boxes>\n\t\t<posX>${board.posx}</posX>\n\t\t<posY>${board.posy}</posY>\n\t</boxes>\n"
        }
        for (board in targets) {
            xml += "\t<targets>\n\t\t<posX>${board.posx}</posX>\n\t\t<posY>${board.posy}</posY>\n\t</targets>\n"
        }
        xml += "\t<player>\n\t\t<posX>${player?.posx}</posX>\n\t\t<posY>${player?.posy}</posY>\n\t</player>\n"
        val xml1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\t<world>\n" +
                "\t$xml" +
                "<world>"
        println(xml1)
    }


    /*fun  drawWorld (boards: ArrayList<Casilla>, boxes: ArrayList<Casilla>, targets: ArrayList<Casilla>){
        //val listaBidimensional = ArrayList<ArrayList<Int>>()
        val casillas1= Array(filas){Array<Casilla>(columnas){null}}
        for (i in (0 until filas)){
            for (j in (0 until filas)){
                for (board in boards) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }

                }
                for (board in boxes) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }
                }
                for (board in targets) {
                    if (board.posx==i && board.posy==j){
                        casillas1[i][j]=board;
                    }
                }
            }

        }

    }*/


}