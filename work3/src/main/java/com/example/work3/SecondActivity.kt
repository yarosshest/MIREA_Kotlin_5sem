package com.example.work3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.work3.databinding.ActivitySecondBinding
import java.io.File


class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val path = this.getExternalFilesDir(null)
        val file = File(path, "date.txt")
        val data = file.readText().split(';')
        val adapter = Adapter(data)
        binding.resycler.adapter = adapter
        binding.resycler.layoutManager = LinearLayoutManager(this)
    }
}