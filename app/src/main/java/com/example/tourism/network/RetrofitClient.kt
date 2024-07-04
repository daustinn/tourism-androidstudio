package com.example.tourism.network

import com.example.tourism.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit: ApiPlaces by lazy {
      Retrofit
          .Builder()
          .baseUrl(Constants.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build()
          .create(ApiPlaces::class.java)
    }
}