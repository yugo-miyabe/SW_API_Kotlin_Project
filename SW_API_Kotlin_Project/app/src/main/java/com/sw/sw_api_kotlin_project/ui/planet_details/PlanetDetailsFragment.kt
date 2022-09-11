package com.sw.sw_api_kotlin_project.ui.planet_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetDetailsBinding
import com.sw.sw_api_kotlin_project.repository.PlanetRepository

class PlanetDetailsFragment : BaseFragment() {
    private lateinit var viewModel: PlanetDetailsViewModel
    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PlanetDetailsFragmentArgs by navArgs()
    private lateinit var planet: Planet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PlanetDetailsFactory(PlanetRepository(swService = SWServiceClient.getService()))
        )[PlanetDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        planet = args.planet
        planet.run {
            binding.nameText.text = name
            binding.rotationPeriodText.text = rotationPeriod
            binding.diameterText.text = diameter
            binding.climateText.text = climate
            binding.gravityText.text = gravity
            binding.terrainText.text = terrain
        }

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}