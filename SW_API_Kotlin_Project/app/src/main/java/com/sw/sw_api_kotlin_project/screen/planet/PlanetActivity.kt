package com.sw.sw_api_kotlin_project.screen.planet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityPlanetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetActivity : AppCompatActivity() {
    private var _binding: ActivityPlanetBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlanetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}