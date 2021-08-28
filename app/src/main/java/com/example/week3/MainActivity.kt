package com.example.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.data.DataSource

class MainActivity : AppCompatActivity() {
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataSource().getProducts()

        adapter = ProductAdapter(data)

        val recyclerProducts = findViewById<RecyclerView>(R.id.recycler_products)
        recyclerProducts.adapter = adapter
    }
}