package com.sw.sw_api_kotlin_project.activities

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        setContentView(binding.root)

        //val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = this.findNavController(R.id.nav_host_fragment)

        setupWithNavController(binding.bottomNavigation, navController)

    }

    override fun initViews() {
        /*
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, APIRootFragment())
            .commit()
         */

    }


    override fun initData() {
        // 何もしない
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}