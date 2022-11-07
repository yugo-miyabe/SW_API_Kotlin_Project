package com.sw.sw_api_kotlin_project.screen.planet.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.screen.planet.PlanetActivity
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.network.model.Planet
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetDetailsBinding
import com.sw.sw_api_kotlin_project.screen.base.PlanetNavListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * 惑星詳細画面
 */
@AndroidEntryPoint
class PlanetDetailsFragment : BaseFragment() {
    private val viewModel: PlanetDetailsViewModel by viewModels()
    private var _binding: FragmentPlanetDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PlanetDetailsFragmentArgs by navArgs()
    private lateinit var planet: Planet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.planetDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                if (activity is PlanetActivity) {
                    view.findNavController().navigateUp()
                } else {
                    activity?.finish()
                }
            }
            title = getString(R.string.films_details_title)
        }
        planet = if (activity is PlanetActivity) {
            args.planet
        } else {
            val navListener = activity as PlanetNavListener
            navListener.getPlanetValue()
        }
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
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteStatus.observe(viewLifecycleOwner) {
            if (it) {
                binding.planetFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.planetFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}