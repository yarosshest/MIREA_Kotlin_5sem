package com.example.work1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.work1.databinding.ActivityMainBinding
import com.example.work1.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}