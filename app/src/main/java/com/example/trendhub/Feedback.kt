package com.example.trendhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class Feedback : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var comment: EditText
    private lateinit var btnsubmit: Button

    private lateinit var databaseRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
        btnsubmit = findViewById(R.id.buttonSubmit)
        name = findViewById(R.id.editName)
        email = findViewById(R.id.editTextEmail)
        comment = findViewById(R.id.editTextComments)



        btnsubmit.setOnClickListener {
                val name = name.text.toString()
                val comment = comment.text.toString()
                val email = email.text.toString()

            addDataToFirebasee(name , email , comment)
        }


    }

    private fun addDataToFirebasee(name : String ,email: String, comment: String ){
        Firebase.database.getReference("Feedbacks").child(name).setValue(
            User(
                name = name ,
                email = email ,
                comment = comment
            )
        ).addOnSuccessListener {
            Toast.makeText(this, "Feedback AAGYA VRO  ☠", Toast.LENGTH_SHORT).show()


        }
    }

}
data class User(
    val name: String = "",
    val email: String = "",
    val comment: String  = ""
)