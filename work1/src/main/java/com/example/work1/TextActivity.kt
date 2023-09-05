package com.example.work1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.work1.databinding.ActivityMainBinding
import com.example.work1.databinding.ActivityTextBinding

class TextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.exit.setOnClickListener {
            finish()
        }
    }
}