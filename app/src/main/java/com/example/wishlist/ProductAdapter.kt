package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.ListItemProductBinding

class ProductAdapter(val productList: List<Product>, val listener: OnProductClickedListener) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    //need to pass in the view to initialize it

    //code that runs to inflate view that was created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.binding.apply {
            productName.text = product.name
            productPrice.text = product.getPrice()
            productDescription.text = product.description
            Glide.with(root)
                .load(product.pictureUrl)
                .into(productImage)

            root.setOnClickListener {
                listener.onProductClicked(position)
            }
        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }
}