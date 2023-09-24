package com.example.work4.menu

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import com.example.work4.R
import com.example.work4.databinding.ActivityMenuBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewMenu) as NavHostFragment
        val nav = navHost.navController

        val bnv = binding.navView
        // Hook your navigation controller to bottom navigation view
        bnv.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bd -> {
                    nav.navigate(R.id.DBViewFragment)
                    return@setOnItemSelectedListener true
                }

                R.id.search -> {
                    nav.navigate(R.id.findFragment)
                    return@setOnItemSelectedListener true
                }

                else -> {false}
            }
        }
    }
}