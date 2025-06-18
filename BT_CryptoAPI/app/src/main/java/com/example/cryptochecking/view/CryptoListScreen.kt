package com.example.cryptochecking.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.cryptochecking.model.CryptoCurrency
import com.example.cryptochecking.viewmodel.CryptoViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoListScreen(viewModel: CryptoViewModel = viewModel()) {
    val coins by viewModel.coins.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crypto Tracker") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(coins) { coin ->
                CryptoItem(coin)
            }
        }
    }
}

@Composable
fun CryptoItem(coin: CryptoCurrency) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(coin.image),
                contentDescription = coin.name,
                modifier = Modifier.size(48.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = coin.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = coin.symbol.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    color = Color.Gray
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${coin.current_price}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "${String.format("%.2f", coin.price_change_percentage_24h)}%",
                    color = if (coin.price_change_percentage_24h >= 0) Color(0xFF4CAF50) else Color(0xFFF44336),
                    fontSize = 14.sp
                )
            }
        }
    }
}


