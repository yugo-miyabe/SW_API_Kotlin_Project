package com.sw.sw_api_kotlin_project.activity.planet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.databinding.ActivityPlanetDetailsBinding
import com.sw.sw_api_kotlin_project.utils.PlanetNavListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanetDetailsActivity : AppCompatActivity(), PlanetNavListener {
    private var _binding: ActivityPlanetDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PlanetActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlanetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun getPlanetValue(): Planet = args.peopleDetails
}