package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageButton
    private lateinit var btnEdit: ImageButton
    private lateinit var imgAvatar: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // file XML ở trên

        // Ánh xạ view
        btnBack = findViewById(R.id.btn_back)
        imgAvatar = findViewById(R.id.img_avatar)
        tvName = findViewById(R.id.tv_name)
        tvLocation = findViewById(R.id.tv_location)

        // Gán dữ liệu mẫu
        tvName.text = "Johan Smith"
        tvLocation.text = "California, USA"

        // Sự kiện nút
        btnBack.setOnClickListener {
            Toast.makeText(this, "Back clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
