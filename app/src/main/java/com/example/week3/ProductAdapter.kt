package com.example.week3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week3.data.model.Product
import com.example.week3.databinding.ListItemProductBinding

class ProductAdapter(val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            productName.text = product.name
            productPrice.text = product.getPrice()
            Glide.with(root)
                .load(product.pictureUrl)
                .into(productImage)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}