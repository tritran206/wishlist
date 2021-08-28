package com.example.wishlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wishlist.data.DataSource
import com.example.wishlist.databinding.ActivityProductDetailBinding

class ProductDetailActivity: AppCompatActivity() {

    private val dataSource = DataSource()
    lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productList = dataSource.getProducts()
        val productIndex = intent.extras?.getInt("PRODUCT_ID") ?: 0
        val product = productList[productIndex]
        binding.textViewProductName.text = product.name
        binding.textViewProductPrice.text = product.getPrice()
    }
}