package com.example.wishlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wishlist.data.model.Product
import com.example.wishlist.databinding.ListItemProductBinding

class ProductAdapter(val listener: OnProductClickedListener) :
    ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    class ProductViewHolder(val binding: ListItemProductBinding) : RecyclerView.ViewHolder(binding.root)
    //need to pass in the view to initialize it

    //code that runs to inflate view that was created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.binding.apply {
            productName.text = product.name
            productPrice.text = product.getFormatPrice()
            productDescription.text = product.description
            Glide.with(root)
                .load(product.pictureUrl)
                .into(productImage)

            root.setOnClickListener {
                listener.onProductClicked(product.id)
            }
        }
    }
}

private class ProductDiffCallback: DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}