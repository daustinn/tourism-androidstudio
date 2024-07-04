package com.example.tourism.network

import com.example.tourism.models.Place
import com.example.tourism.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiPlaces {
    @GET("places")
    suspend fun getPlaces(@Query("api_key") apiKey: String = Constants.API_KEY): List<Place>

    @GET("places/{id}")
    suspend fun getPlace(@Path("id") placeId: String, @Query("api_key") apiKey: String = Constants.API_KEY): Place

    @GET("places")
    suspend fun getPlaceByCategory(
        @Query("category") category: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): List<Place>
}