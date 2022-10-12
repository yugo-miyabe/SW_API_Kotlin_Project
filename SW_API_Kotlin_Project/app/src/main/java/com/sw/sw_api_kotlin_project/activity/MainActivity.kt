package com.sw.sw_api_kotlin_project.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController((navController!!.findNavController()))
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}