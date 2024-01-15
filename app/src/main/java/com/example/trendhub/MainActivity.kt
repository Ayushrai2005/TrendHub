package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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

        FirebaseDatabase.getInstance().getReference("Products")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    listOfProduct.clear()
                    for(dataSnapShot in snapshot.children){
                        val product = dataSnapShot.getValue(Product::class.java)
                        listOfProduct.add(product!!)
                    }
                    productAdapter = ProductAdapter(listOfProduct, this@MainActivity)
                    rv.adapter=productAdapter
                    rv.layoutManager= GridLayoutManager(this@MainActivity,2)
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })




        fab.setOnClickListener {
            startActivity(
                Intent(this , LoginActivity::class.java)
            )
        }






    }
}