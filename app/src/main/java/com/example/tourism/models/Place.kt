package com.example.tourism.models

import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("id")
    val id : String,

    @SerializedName("title")
    val title : String,

    @SerializedName("thumbnail")
    val thumbnail : String,

    @SerializedName("images")
    val images : List<String>,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude : Double,

    @SerializedName("description")
    val description : String,

    @SerializedName("location")
    val location : String,

    @SerializedName("category")
    val category : String,

    @SerializedName("rating")
    val rating : Double,
)
