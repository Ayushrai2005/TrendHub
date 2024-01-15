package com.example.trendhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
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


    //lateOp

    // Make sure to use the FloatingActionButton for all the FABs
    private lateinit var mAddFab: FloatingActionButton
    private lateinit var mAddAlarmFab: FloatingActionButton
    private lateinit var mAddPersonFab: FloatingActionButton

    // These are taken to make visible and invisible along with FABs
    private lateinit var addAlarmActionText: TextView
    private lateinit var addPersonActionText: TextView

    // to check whether sub FAB buttons are visible or not.
    private var isAllFabsVisible: Boolean? = null



    //lateop





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //lateop


        // Register all the FABs with their IDs This FAB button is the Parent
        mAddFab = findViewById(R.id.fab)

        // FAB button
        mAddAlarmFab = findViewById(R.id.addProduct)
        mAddPersonFab = findViewById(R.id.person_fab)

        // Also register the action name text, of all the FABs.
        addAlarmActionText = findViewById(R.id.add_alarm_action_text)
        addPersonActionText = findViewById(R.id.add_person_action_text)

        // Now set all the FABs and all the action name texts as GONE
        mAddAlarmFab.visibility = View.GONE
        mAddPersonFab.visibility = View.GONE
        addAlarmActionText.visibility = View.GONE
        addPersonActionText.visibility = View.GONE

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false


        mAddFab.setOnClickListener(View.OnClickListener {
            (if (!isAllFabsVisible!!) {
                // when isAllFabsVisible becomes true make all
                // the action name texts and FABs VISIBLE
                mAddAlarmFab.show()
                mAddPersonFab.show()
                addAlarmActionText.visibility = View.VISIBLE
                addPersonActionText.visibility = View.VISIBLE

                // make the boolean variable true as we
                // have set the sub FABs visibility to GONE
                true
            } else {
                // when isAllFabsVisible becomes true make
                // all the action name texts and FABs GONE.
                mAddAlarmFab.hide()
                mAddPersonFab.hide()
                addAlarmActionText.visibility = View.GONE
                addPersonActionText.visibility = View.GONE

                // make the boolean variable false as we
                // have set the sub FABs visibility to GONE
                false
            }).also { isAllFabsVisible = it }
        })








        //lateop



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




        mAddAlarmFab.setOnClickListener {
            startActivity(
                Intent(this , LoginActivity::class.java)
            )
        }


        mAddPersonFab.setOnClickListener {
            startActivity(
                Intent(this,about::class.java)
            )
        }







    }
}