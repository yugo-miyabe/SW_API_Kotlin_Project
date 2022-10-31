package com.sw.sw_api_kotlin_project.screen.film

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sw.sw_api_kotlin_project.databinding.ActivityFilmBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmActivity : AppCompatActivity() {
    private var _binding: ActivityFilmBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}