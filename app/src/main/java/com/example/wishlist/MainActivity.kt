package com.example.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.data.DataSource

class MainActivity :
    AppCompatActivity(),
    OnProductClickedListener
{
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataSource().getProducts()

        adapter = ProductAdapter(data, this)

        val recyclerProducts = findViewById<RecyclerView>(R.id.recycler_products)
        recyclerProducts.adapter = adapter
    }

    override fun onProductClicked(productIndex: Int) {
        val intent = Intent(this, ProductDetailActivity::class.java)
            .putExtra("PRODUCT_ID", productIndex)

        startActivity(intent)
    }

}

interface OnProductClickedListener {
    fun onProductClicked(productIndex: Int)
}