package com.example.signup_n

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Welcome : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringArrayExtra(SignIn.KEY)
        val nam = findViewById<TextInputEditText>(R.id.name)
        val num = findViewById<TextInputEditText>(R.id.num1)
        val mail = findViewById<TextInputEditText>(R.id.email)
        val submit = findViewById<Button>(R.id.btn2)

        val nam1 = name?.get(2).toString()
        val mail1 = name?.get(1).toString()

        /* val welcome = findViewById<TextView>(R.id.textView3)
        welcome.text = "ID : ${name?.get(0)} \n Email : ${name?.get(1)} \n Name : ${name?.get(2)}"*/
        nam.setText(nam1)
        mail.setText(mail1)
        submit.setOnClickListener {
            val number = num.text.toString()
            if (number.isEmpty()) {
                Toast.makeText(this, "Enter phone number", Toast.LENGTH_SHORT).show()

            } else {
                val contactObj = Contacts(nam1, mail1, number)
                database = FirebaseDatabase.getInstance().getReference("Contacts")
                database.child(number).setValue(contactObj).addOnSuccessListener {
                    num.text?.clear()
                    dialog = Dialog(this)
                    dialog.setContentView(R.layout.custom)
                    val yes = dialog.findViewById<Button>(R.id.yes)
                    val no = dialog.findViewById<Button>(R.id.no)
                    dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.rounded_bg))
                    no.setOnClickListener() {
                        finish()
                    }
                    yes.setOnClickListener() {
                        dialog.dismiss()
                    }
                    dialog.show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}
