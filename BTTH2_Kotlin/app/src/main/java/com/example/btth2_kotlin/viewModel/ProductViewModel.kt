package com.example.btth2_kotlin.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.btth2_kotlin.data.model.Product
import com.example.btth2_kotlin.data.api.RetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var product by mutableStateOf<Product?>(null)
        private set

    var isLoading by mutableStateOf(true)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    init {
        viewModelScope.launch {
            try {
                product = RetrofitClient.api.getProduct()
            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}