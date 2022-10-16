package com.sw.sw_api_kotlin_project.activity.film

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.ActivityFilmBinding
import com.sw.sw_api_kotlin_project.databinding.ActivityFilmDetailsBinding
import com.sw.sw_api_kotlin_project.databinding.ActivityPeopleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailsActivity : AppCompatActivity() {
    private var _binding: ActivityFilmDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}