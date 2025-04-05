package com.example.pix.data.flickr

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FlickrRetrofit {
    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.flickr.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}