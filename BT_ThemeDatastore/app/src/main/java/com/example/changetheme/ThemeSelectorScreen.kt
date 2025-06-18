package com.example.changetheme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ThemeSelectorScreen(viewModel: ThemeViewModel) {
    val themeColor by viewModel.themeColor.collectAsState()

    val colorOptions = listOf(
        Color.White,
        Color(0xFF1C1C1E),   // Dark
        Color(0xFFFF2D87),   // Pink
        Color(0xFF87CEFA)    // Light Blue
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(themeColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Setting",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = if (themeColor == Color.White) Color.Black else Color.White
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            colorOptions.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = 2.dp,
                            color = if (color == themeColor) Color.Gray else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable { viewModel.saveColor(color) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { /* Điều hướng nếu cần */ }) {
            Text("Apply")
        }
    }
}

