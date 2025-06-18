package com.example.cryptochecking.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptochecking.model.CryptoCurrency
import com.example.cryptochecking.api.ApiClient
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val _coins = MutableStateFlow<List<CryptoCurrency>>(emptyList())
    val coins: StateFlow<List<CryptoCurrency>> = _coins.asStateFlow()

    init {
        startAutoRefresh()
    }

    private fun startAutoRefresh() {
        viewModelScope.launch {
            while (true) {
                fetchCoins()
                delay(1000L)
            }
        }
    }

    private suspend fun fetchCoins() {
        runCatching {
            ApiClient.api.getMarketData()
        }.onSuccess { data ->
            _coins.value = data
        }.onFailure { e ->
            e.printStackTrace()
            // optionally emit UI error state here
        }
    }
}

