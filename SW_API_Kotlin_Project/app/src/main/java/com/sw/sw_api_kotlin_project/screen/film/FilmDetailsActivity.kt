package com.sw.sw_api_kotlin_project.screen.film

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.databinding.ActivityFilmDetailsBinding
import com.sw.sw_api_kotlin_project.screen.base.FilmNavListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmDetailsActivity : AppCompatActivity(), FilmNavListener {
    private var _binding: ActivityFilmDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: FilmDetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun getFilmValue(): Film = args.filmDetails
}