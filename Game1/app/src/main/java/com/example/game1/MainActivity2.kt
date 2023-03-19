package com.example.game1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi

class MainActivity2 : AppCompatActivity() {
    var input: String = ""
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val bundle = intent.extras
        if (bundle != null) {
            this.input = bundle.getSerializable("input", String::class.java).toString()
        }
    }
}