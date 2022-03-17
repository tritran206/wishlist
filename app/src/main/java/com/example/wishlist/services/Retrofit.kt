package com.example.wishlist.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://mobile-tha-server.firebaseapp.com"
    private var instance: Retrofit? = null

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
        return instance!!
    }
}