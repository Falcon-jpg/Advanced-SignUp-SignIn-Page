package com.example.signup_n

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name=intent.getStringArrayExtra(SignIn.KEY)

        val welcome = findViewById<TextView>(R.id.textView3)
        welcome.text = "ID : ${name?.get(0)} \n Email : ${name?.get(1)} \n Name : ${name?.get(2)}"
    }
}