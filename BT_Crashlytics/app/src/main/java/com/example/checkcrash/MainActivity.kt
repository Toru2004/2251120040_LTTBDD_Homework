package com.example.checkcrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checkcrash.ui.theme.CheckCrashAppTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.firebase.crashlytics.FirebaseCrashlytics


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckCrashAppTheme {
                    CalculatorScreen()
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<String?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Text("Máy tính của Phúc", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = input1,
            onValueChange = { input1 = it },
            label = { Text("Số thứ nhất") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = input2,
            onValueChange = { input2 = it },
            label = { Text("Số thứ hai") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { result = calculate(input1, input2, "+", context) }) {
                Text("+")
            }
            Button(onClick = { result = calculate(input1, input2, "-", context) }) {
                Text("-")
            }
            Button(onClick = { result = calculate(input1, input2, "*", context) }) {
                Text("*")
            }
            Button(onClick = { result = calculate(input1, input2, "/", context) }) {
                Text(":")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        result?.let {
            Text("Kết quả: $it", style = MaterialTheme.typography.bodyLarge)
        }

        error?.let {
            Text("Lỗi: $it", color = Color.Red)
        }
    }
}

fun calculate(a: String, b: String, op: String, context: Context): String {
    return try {
        val num1 = a.toDouble()
        val num2 = b.toDouble()

        if (op == "/" && num2 == 0.0) {
            throw RuntimeException("Không thể chia cho 0")
        }

        when (op) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "*" -> (num1 * num2).toString()
            "/" -> (num1 / num2).toString()
            else -> "Phép toán không hợp lệ"
        }
    } catch (e: Exception) {
        Log.e("Calculator", "Lỗi tính toán", e)
        "Lỗi đầu vào: ${e.localizedMessage}"
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CheckCrashAppTheme {
//        Greeting("Android")
//    }
//}