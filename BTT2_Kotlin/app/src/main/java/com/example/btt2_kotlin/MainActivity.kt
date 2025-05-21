package com.example.btt2_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val buttonCheck = findViewById<Button>(R.id.buttonCheck)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCheck.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val ageText = editTextAge.text.toString().trim()

            if (name.isEmpty() || ageText.isEmpty()) {
                textViewResult.text = "Vui lòng nhập đầy đủ thông tin!"
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null || age < 0) {
                textViewResult.text = "Tuổi không hợp lệ!"
                return@setOnClickListener
            }

            val category = when {
                age > 65 -> "Người già"
                age in 7..65 -> "Người lớn"
                age in 3..6 -> "Trẻ em"
                else -> "Em bé"
            }

            textViewResult.text = "Họ tên: $name\nTuổi: $age\nPhân loại: $category"
        }
    }
}
