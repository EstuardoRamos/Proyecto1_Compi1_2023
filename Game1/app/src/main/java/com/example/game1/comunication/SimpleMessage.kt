package com.example.game1.comunication

class SimpleMessage(val message: String) : java.io.Serializable {

    companion object {
        private const val serialVersionUID = 6529685098267757690L
    }

    override fun toString(): String {
        return "SimpleMessage(message='$message')"
    }
}