package com.example.dicodingevent.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.dicodingevent.BaseActivity
import com.example.dicodingevent.R
import com.example.dicodingevent.databinding.ActivityBottomBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomActivity : BaseActivity() {

    private lateinit var binding: ActivityBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setTheme()

        binding = ActivityBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bottom)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setupWithNavController(navController)

        binding.fabTheme.setOnClickListener {
            switchTheme()
        }

        if (currentTheme == LIGHT) {
            binding.fabTheme.setImageResource(com.example.dicodingevent.R.drawable.light_theme)
        } else {
            binding.fabTheme.setImageResource(com.example.dicodingevent.R.drawable.dark_theme)
        }
    }
}