package com.sw.sw_api_kotlin_project.screen.planet.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetDetailsBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 惑星詳細画面
 */
@AndroidEntryPoint
class PlanetDetailsFragment :
    BaseFragment<PlanetDetailsViewModel, FragmentPlanetDetailsBinding>() {
    override val viewModel: PlanetDetailsViewModel by viewModels()
    private val args: PlanetDetailsFragmentArgs by navArgs()
    private lateinit var planet: Planet

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlanetDetailsBinding =
        FragmentPlanetDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        planet = args.planet
        planet.run {
            binding.nameText.text = name
            binding.rotationPeriodText.text = rotationPeriod
            binding.diameterText.text = diameter
            binding.climateText.text = climate
            binding.gravityText.text = gravity
            binding.terrainText.text = terrain
        }
        binding.planetFavoriteMark.setOnClickListener {
            viewModel.toggleFavorite(planet)
        }

        viewModel.getFavoriteState(planet.name)

        viewModel.favoriteStatus.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.planetFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.planetFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }
}
