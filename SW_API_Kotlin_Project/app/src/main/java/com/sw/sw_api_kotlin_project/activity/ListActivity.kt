package com.sw.sw_api_kotlin_project.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListActivity : AppCompatActivity() {
    private var _binding: ActivityListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}