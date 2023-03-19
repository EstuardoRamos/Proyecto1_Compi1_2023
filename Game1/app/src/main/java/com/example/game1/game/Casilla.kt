package com.example.game1.game

class Casilla(
    var posx:Int,
    var posy:Int,
    var tipo:String,
    var color: String ) {

    override fun toString(): String {
        return super.toString()
    }
    class Casilla constructor(posx: Int, posy: Int, tipo: String, color: String?)  {

    }


    fun getposx(): Int {
        return posx
    }

    fun setposx(posx: Int) {
        this.posx = posx
    }

    fun getposy(): Int {
        return posy
    }

    fun setposy(posy: Int) {
        this.posy = posy
    }

    fun gettipo(): String? {
        return tipo
    }

    fun settipo(tipo: String) {
        this.tipo = tipo
    }

    fun getcolor(): String? {
        return color
    }

    fun setcolor(color: String) {
        this.color = color
    }

    fun infoCasilla() {
        println("Type= $tipo - PosX= $posx - Posy= $posy")
    }

}