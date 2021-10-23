package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.ListItemCartBinding

class ShoppingCartAdapter(val cartItems: List<Product>): RecyclerView.Adapter<ShoppingCartAdapter.CartViewHolder>() {
    class CartViewHolder(val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ListItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.binding.apply {
            productName.text = product.name
            productPrice.text = product.getFormatPrice()
            Glide.with(root)
                .load(product.pictureUrl)
                .into(productImage)
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }


}