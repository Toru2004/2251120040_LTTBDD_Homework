package com.example.btth2_kotlin.data.api

import com.example.btth2_kotlin.data.model.Product
import retrofit2.http.GET

interface ProductApi {
    @GET("product")
    suspend fun getProduct(): Product
}