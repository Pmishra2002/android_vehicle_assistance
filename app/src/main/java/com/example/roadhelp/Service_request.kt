package com.example.roadhelp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.util.Log

 class Service_request : AppCompatActivity() {
    private lateinit var etname:EditText
    private lateinit var etvtype:EditText
    private lateinit var etvid:EditText
    private lateinit var etlocation:EditText
    private lateinit var btnsubmit:Button
    private lateinit var dbRef:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_request)
        etname = findViewById(R.id.name)
        etvtype = findViewById(R.id.vtype)
        etvid = findViewById(R.id.vnumber)
        etlocation = findViewById(R.id.place)
        btnsubmit = findViewById(R.id.btn_request)

        dbRef=FirebaseDatabase.getInstance().getReference("User")

        btnsubmit.setOnClickListener { saveUserDetails() }
    }
    private fun saveUserDetails()
    {
        //grtting values
        val userName=etname.text.toString()
        val vehicleName=etvtype.text.toString()
        val vehiclenumber=etvid.text.toString()
        val location=etlocation.text.toString()

        if(userName.isEmpty()){
            etname.error="Please enter your Full Name"
        }
        if(vehicleName.isEmpty()){
            etvtype.error="Please enter your Vehicle Type"
        }
        if(vehiclenumber.isEmpty()){
            etvid.error="Please enter your Vehicle Number"
        }
        if(location.isEmpty()){
            etlocation.error="Please enter your Location in Details"
        }

       val userId = dbRef.push().key!!
        print(userId)
        Log.d("hello",userId.toString())

        val user=UserModel(userId, userName,vehicleName,vehiclenumber,location)
        dbRef.child(userId).setValue(user)
            .addOnCompleteListener{
                Toast.makeText(applicationContext,"Request Submitted Successfully",Toast.LENGTH_SHORT).show()
                etname.text.clear()
                etvtype.text.clear()
                etvid.text.clear()
                etlocation.text.clear()
            }.addOnFailureListener { err ->
                Toast.makeText(applicationContext,"Error ${err.message}",Toast.LENGTH_SHORT).show()
            }


    }
}