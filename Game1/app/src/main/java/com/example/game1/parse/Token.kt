package com.example.game1.parse

class Token (val value: String?, val type: Int, val line: Int, val column: Int) {

    override fun toString(): String {
        return "Token(value=$value, type=$type, line=$line, column=$column)"
    }


}