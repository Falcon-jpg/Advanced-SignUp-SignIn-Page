package com.example.signup_n

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignIn : AppCompatActivity() {

    companion object {
        const val KEY= "com.example.signup_n.SignIn.KEY"
    }

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btn=findViewById<Button>(R.id.btn2)
        val userId=findViewById<TextInputEditText>(R.id.etID)
        val pass=findViewById<TextInputEditText>(R.id.etPass2)
        val btn2=findViewById<Button>(R.id.button)

        btn2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener{
            val passW=pass.text.toString()
            val user=userId.text.toString()
            if(user.isNotEmpty()){
                readData(user,passW)
            }else{
                Toast.makeText(this,"Enter the UserID",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(user: String , passW:String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(user).get().addOnSuccessListener {
                if(it.exists()){
                    //new intent
                    val storedName= it.child("name").value.toString()
                    val storedID= it.child("uniqueID").value.toString()
                    val storedMail= it.child("email").value.toString()
                    val storedPass= it.child("pass").value.toString()
                    if(storedPass==passW){
                        //intent
                        val intent = Intent(this,Welcome::class.java)
                        val array= arrayOf(storedID,storedMail,storedName)
                        intent.putExtra(KEY,array)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Password Wrong",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"User Not Found",Toast.LENGTH_SHORT).show()
                }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}