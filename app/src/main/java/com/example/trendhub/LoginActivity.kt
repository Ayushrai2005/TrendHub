package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText

    private lateinit var auth : FirebaseAuth
    private lateinit var btnLogin : Button
    private lateinit var btnSignup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        edtEmail = findViewById(R.id.edt_email)
        edtPassword= findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignup = findViewById(R.id.btn_signup)

        auth = Firebase.auth



        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email , password)
        }

        btnSignup.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signup(email , password)
        }
    }

    private fun login(email : String , password : String) {
        auth.signInWithEmailAndPassword(email , password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                //open upload Product Screen
                    startActivity(
                        Intent(this , uploadProductActivity::class.java)
                    )

            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                )
            }
        }
    }

    private fun signup(email : String ,password :String){
        auth.createUserWithEmailAndPassword(email , password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //open upload Product Screen
                    Toast.makeText(this, "SignUp Successfull", Toast.LENGTH_SHORT).show()
                    

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    )
                }
            }


    }


}