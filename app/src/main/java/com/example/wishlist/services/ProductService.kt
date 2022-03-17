package com.example.wishlist.services

import androidx.lifecycle.LiveData
import com.example.wishlist.data.model.Product
import com.example.wishlist.data.model.ProductResponse
import retrofit2.Call
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET("/walmartproducts/{pageNumber}/{pageSize}")
    fun getProducts(
        @Path("pageNumber") pageNumber: Int,
        @Path("pageSize") pageSize: Int
    ): Call<ProductResponse>

    companion object {
        val instance: ProductService by lazy {
            RetrofitHelper.getInstance().create(
                ProductService::class.java
            )
        }
    }
}
