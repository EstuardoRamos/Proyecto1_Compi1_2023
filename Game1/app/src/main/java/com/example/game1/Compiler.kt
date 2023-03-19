package com.example.game1

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.text.format.Formatter
import com.example.game1.R
import com.example.game1.analisis.CounterLexer
import com.example.game1.analisis.parser
import com.example.game1.comunication.AsyncResponse
import com.example.game1.comunication.MyTask
import com.example.game1.comunication.NetworkThread
import com.example.game1.comunication.SimpleMessage
import com.example.game1.game.Manejador
import com.example.game1.game.World
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.StringReader
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.Socket

class Compiler : AppCompatActivity(), AsyncResponse {
    val ipServer: String = "172.17.0.1"
    val portServer: Int = 9090
    private val TAG: String = "Compiler"
    var input: String = "";
    private var response: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compiler)

        val editor: EditText = findViewById(R.id.etInput)
        val backspaceButton: Button = findViewById(R.id.btnClear)
        val compileButton: Button = findViewById(R.id.btnCompile)

        compileButton.setOnClickListener(View.OnClickListener {
            this.input = editor.text.toString()
            //val world= World()

           /* val lexer = CounterLexer(StringReader(input))
            val parser = parser(lexer)
            val world=parser.parse().value as World;*/


            /* Log.println(Log.INFO, TAG, "Compiling")

            val intent = Intent(this, DrawWorld::class.java).apply {
                putExtra("world_object", world) // Agrega el objeto al Intent
            }*/
            //mo funciona desde aqui
            //startActivity(intent) // Inicia el segundo Activity
          /*  val intent = Intent(this, DrawWorld::class.java)
            intent.putExtra("world", world)
            startActivity(intent)*/
            //goToDrawActivity(input)
            //this.input = editor.text.toString()
           // val text = editor.text.toString()
           // this.sendMessage("hoal que hace" )
           // var manejador:Manejador= Manejador()

            Thread(Runnable {
                val socket = Socket(ipServer, 8080)

                val outputStream = socket.getOutputStream()
                val dataOutputStream = DataOutputStream(outputStream)

                val mensaje = "Hola, servidor!"
                dataOutputStream.writeUTF(input)

                val inputStream = DataInputStream(socket.getInputStream())
                response = inputStream.readUTF()
                val r: String? = response as String?
                println("esta es una respuesta uqe  me da el server\n"+ response)
                //manejador.leer(response+"")
                recibirString(response+"")
                dataOutputStream.close()
                inputStream.close()
                outputStream.close()
                socket.close()
            }).start()

        })
    }

    private fun recibirString(res: String) {
        val miIntent = Intent(this@Compiler, MainActivity::class.java)
        val miBundle = Bundle()
        // miBundle.putSerializable("world", world)
        miBundle.putString("input", res)
        miIntent.putExtras(miBundle)
        startActivity(miIntent)

    }

    private fun sendMessage(message: String) {
        val simpleMessage = SimpleMessage(message)
        val task = MyTask(ipServer, portServer, simpleMessage)
        task.delegate = this
        task.execute()
    }

    override fun processResponse(output: String?) {
        if (output != null) {
           // textView?.text = output
        } else {
            //textView?.text = "Something went wrong"
        }
    }

    private fun getIp(): String {
        NetworkInterface.getNetworkInterfaces().toList().map {
            it.inetAddresses.toList().find {
                !it.isLoopbackAddress && it is Inet4Address
            }?.let {
                return it.hostAddress
            }
        }

        return ""
    }

    private fun getWifiIp(): String {
        val context = this.applicationContext;
        val wifiManager: WifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val ip: String = Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
        return ip;
    }

    private fun comunicando(){

    }
}