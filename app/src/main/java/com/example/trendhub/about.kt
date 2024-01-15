package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class about : AppCompatActivity() {
    private lateinit var feedBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        feedBack = findViewById(R.id.visitFeedback)
        
        feedBack.setOnClickListener {
            startActivity(
            Intent(this,Feedback::class.java)
        )
            Toast.makeText(this, "FillUp The Form", Toast.LENGTH_SHORT).show()
            
        }
          




    }
}