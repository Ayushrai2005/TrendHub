package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var rv : RecyclerView
    val listOfProduct = mutableListOf<Product>()
    private lateinit var  productAdapter : ProductAdapter
    private lateinit var fab : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv = findViewById(R.id.rv)
        fab = findViewById(R.id.fab)

        val p1 = Product(
            productName = "Acer Nitro v",
            productPrice = "74440",
            productDescription = "This is high end gaming laptop",
            productImage = ""
        )
        val p2 = Product(
            productName = "Acer Predator",
            productPrice = "114000",
            productDescription = "This is super high end gaming laptop",
            productImage = ""
        )
        val p3 = Product(
            productName = "Acer Aspire",
            productPrice = "40000",
            productDescription = "This is mid end laptop",
            productImage = ""
        )

        listOfProduct.add(p1)
        listOfProduct.add(p2)
        listOfProduct.add(p3)
        listOfProduct.add(p3)


        productAdapter = ProductAdapter(listOfProduct)
        rv.adapter=productAdapter
        rv.layoutManager= GridLayoutManager(this,2)

        fab.setOnClickListener {
            startActivity(
                Intent(this , LoginActivity::class.java)
            )
        }






    }
}