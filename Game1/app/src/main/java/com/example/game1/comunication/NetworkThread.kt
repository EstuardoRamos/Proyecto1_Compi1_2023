package com.example.game1.comunication

import android.util.Log
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.IOException
import java.net.Socket

class NetworkThread : Thread() {
    private var response: String? = null

    override fun run() {
        try {
            val socket = Socket("172.17.0.1", 9090)
            val outputStream = DataOutputStream(socket.getOutputStream())

            // Enviar mensaje al servidor
            val mensaje = "Hola, servidor!"
            outputStream.writeUTF(mensaje)
            outputStream.flush()

            // Leer respuesta del servidor
            val inputStream = DataInputStream(socket.getInputStream())
            response = inputStream.readUTF()

            // Cerrar flujos de entrada y salida y el socket
            inputStream.close()
            outputStream.close()
            socket.close()

        } catch (e: IOException) {
            Log.e("NetworkThread", "Error: ${e.message}")
        }
    }

    fun getResponse(): String? {
        return response
    }
}
