package com.example.game1.comunication

import android.os.AsyncTask

import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.net.Socket



class MyTask(val ip: String, val port: Int, val message: SimpleMessage) : AsyncTask<Void, Void, String>() {

    var delegate: AsyncResponse? = null

    override fun doInBackground(vararg p0: Void?): String {
        val socket = Socket(ip, port)
        val outputStream = ObjectOutputStream(socket.getOutputStream())
        val inputStream = ObjectInputStream(socket.getInputStream())

        // sending message
        outputStream.writeObject(message)

        // receiving message
        val response: SimpleMessage = inputStream.readObject() as SimpleMessage;
        // showing message
        return response.toString()
    }

    override fun onPostExecute(result: String?) {
        delegate?.processResponse(result)
    }
}