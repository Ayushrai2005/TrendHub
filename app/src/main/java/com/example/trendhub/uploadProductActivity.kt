package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar

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
        if(requestCode == 1 && resultCode == 101) {
            val uri = data!!.data
            productPreview.setImageURI(uri)
        }
    }
}