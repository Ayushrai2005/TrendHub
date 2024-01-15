package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class uploadProductActivity : AppCompatActivity() {

    private  lateinit var productPreview : ImageView
    private lateinit var edtProductName : EditText
    private lateinit var edtProductprice : EditText
    private lateinit var edtProductDescription : EditText

    private lateinit var btnSelectProduct : Button
    private lateinit var btnUploadProduct : Button

    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_product)

        productPreview =findViewById(R.id.img_productpreview)
        edtProductName =findViewById(R.id.edt_productname)
        edtProductprice =findViewById(R.id.edt_productprice)
        edtProductDescription =findViewById(R.id.edt_productDes)

        btnSelectProduct = findViewById(R.id.btn_selectproduct)
        btnUploadProduct = findViewById(R.id.btn_uploadproduct)

        progressBar = findViewById(R.id.progress_Bar)

        btnSelectProduct.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent , 101)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == RESULT_OK) {
            val uri = data!!.data
            productPreview.setImageURI(uri)

            btnUploadProduct.setOnClickListener {
                // Upload Image to firebase cloud &
                // get the link of the image
                progressBar.visibility = View.VISIBLE
                val productName = edtProductName.text.toString()
                val productPrice = edtProductprice.text.toString()
                val productDes =  edtProductDescription.text.toString()

                val fileName = UUID.randomUUID().toString()+ ".jpg"
                val storageRef = FirebaseStorage.getInstance().reference.child("Product_Images/$fileName" )
                storageRef.putFile(uri!!).addOnSuccessListener {
                    val result = it.metadata?.reference?.downloadUrl
                    result?.addOnSuccessListener {
                        progressBar.visibility = View.GONE
                        uploadProduct(
                            productName,
                            productPrice,
                            productDes,
                            it.toString()
                        )
                    }
                }

            }
        }
    }

    private fun uploadProduct(name : String , des : String , price :String , link : String){
        Firebase.database.getReference("Products").child(name).setValue(
            Product(
                productName = name ,
                productDescription = des,
                productImage =  link ,
                productPrice = price
            )
        ).addOnSuccessListener {
            progressBar.visibility = View.GONE
            Toast.makeText(this, "product uploaded Successfully", Toast.LENGTH_SHORT).show()


        }
    }
}