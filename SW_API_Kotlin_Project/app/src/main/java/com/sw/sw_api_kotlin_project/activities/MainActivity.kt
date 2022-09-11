package com.sw.sw_api_kotlin_project.activities

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigation, navController)
    }

    override fun initViews() {
        // 何もしない
    }


    override fun initData() {
        // 何もしない
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}