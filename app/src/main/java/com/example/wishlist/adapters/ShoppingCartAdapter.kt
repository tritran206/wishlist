package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.ListItemCartBinding

class ShoppingCartAdapter(): ListAdapter<Product, ShoppingCartAdapter.CartViewHolder>(CartItemDiffCallback()) {

    class CartViewHolder(val binding: ListItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ListItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.apply {
            productName.text = product.name
            productPrice.text = product.getFormatPrice()
            Glide.with(root)
                .load(product.pictureUrl)
                .into(productImage)
        }
    }
}

class CartItemDiffCallback(): DiffUtil.ItemCallback<Product>() {

    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}