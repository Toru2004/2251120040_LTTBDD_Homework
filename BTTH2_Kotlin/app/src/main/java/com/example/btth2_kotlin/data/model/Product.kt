package com.example.btth2_kotlin.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val name: String,
    val id: String,
    @SerializedName("des")
    val description: String,
    val price: Double,
    @SerializedName("imgURL")
    val image: String
)
