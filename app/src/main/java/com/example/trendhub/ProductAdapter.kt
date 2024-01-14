package com.example.trendhub

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(

    private val listOfProducts : List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){
    class ProductViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView){

        val productImg : ImageView = itemView.findViewById(R.id.product_img)
        val productName : TextView = itemView.findViewById(R.id.text_product_name)
        val productPrice : TextView= itemView.findViewById(R.id.text_product_price)
        val productDesc : TextView = itemView.findViewById(R.id.text_product_description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product , parent , false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfProducts.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = listOfProducts[position]
        holder.productName.text=currentProduct.productName
        holder.productPrice.text= currentProduct.productPrice
        holder.productDesc.text = currentProduct.productDescription
        //Glide


    }
}