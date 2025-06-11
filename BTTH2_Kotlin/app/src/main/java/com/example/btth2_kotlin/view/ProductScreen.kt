package com.example.btth2_kotlin.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.btth2_kotlin.viewModel.ProductViewModel
import com.example.btth2_kotlin.data.model.Product

@Composable
fun ProductScreen(viewModel: ProductViewModel = viewModel()) {
    val product = viewModel.product
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    when {
        isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Lỗi: $error", color = Color.Red)
            }
        }

        product != null -> {
            ProductDetail(product)
        }
    }
}

@Composable
fun ProductDetail(product: Product) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.name,
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(product.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Giá: ${product.price}", color = Color.Red, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(product.description)
    }
}