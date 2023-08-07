package com.example.roadhelp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.app.Application
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    private lateinit var btnRequestData: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
val firebase : DatabaseReference=FirebaseDatabase.getInstance().getReference()

        btnRequestData=findViewById(R.id.btnRequestData)
        btnRequestData.setOnClickListener {
            val intent=Intent(this,Service_request::class.java)

            startActivity(intent)
           // print("dfjhgvdjhfvbj")

        }

        }
    }
