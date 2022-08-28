package com.sw.sw_api_kotlin_project.activities

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setupWithNavController(navigation, navController)
    }

    override fun initViews() {}


    override fun initData() {
        // 何もしない
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}