package com.example.cryptochecking.api

import com.example.cryptochecking.model.CryptoCurrency
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getMarketData(
        @Query("vs_currency") currency: String = "usd"
    ): List<CryptoCurrency>
}

object ApiClient {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.coingecko.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CoinGeckoApi = retrofit.create(CoinGeckoApi::class.java)
}
