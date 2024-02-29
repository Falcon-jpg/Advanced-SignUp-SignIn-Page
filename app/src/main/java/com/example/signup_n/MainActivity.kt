package com.example.signup_n

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signBtn = findViewById<Button>(R.id.btn)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etPass = findViewById<TextInputEditText>(R.id.etPass)
        val etUser = findViewById<TextInputEditText>(R.id.etUserID)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val signBtn2 = findViewById<TextView>(R.id.tV2)

        //This comment out code is used to disable/enable signUP button depending on null or not null value in editText
        /*signBtn.isEnabled = false

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val name = etName.text.toString()
                val pass = etPass.text.toString()
                val user = etUser.text.toString()
                val mail = etMail.text.toString()

                signBtn.isEnabled = name.isNotEmpty() && pass.isNotEmpty() && user.isNotEmpty() && mail.isNotEmpty()
            }
        }

        etName.addTextChangedListener(textWatcher)
        etPass.addTextChangedListener(textWatcher)
        etUser.addTextChangedListener(textWatcher)
        etMail.addTextChangedListener(textWatcher)*/

        signBtn2.setOnClickListener{
            val intent=Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        signBtn.setOnClickListener(){
            val name=etName.text.toString()
            val pass=etPass.text.toString()
            val user=etUser.text.toString()
            val mail=etMail.text.toString()


            if (name.isEmpty() || pass.isEmpty() || user.isEmpty() || mail.isEmpty()) {
                Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show()
            } else {
                val userObj = Users(name, mail, user, pass)
                database = FirebaseDatabase.getInstance().getReference("Users")
                database.child(user).setValue(userObj).addOnSuccessListener {
                    etMail.text?.clear()
                    etName.text?.clear()
                    etPass.text?.clear()
                    etUser.text?.clear()
                    Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }



        }
    }
}