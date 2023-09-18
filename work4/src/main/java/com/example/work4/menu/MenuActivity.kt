package com.example.work4.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.work4.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }
}