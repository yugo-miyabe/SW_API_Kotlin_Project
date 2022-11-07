package com.sw.sw_api_kotlin_project.screen.planet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetListBinding
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.screen.planet.PlanetAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * 惑星一覧画面
 */
@AndroidEntryPoint
class PlanetListFragment : BaseFragment() {
    private val viewModel: PlanetViewModel by viewModels()
    private var _binding: FragmentPlanetListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.planetListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                activity?.finish()
            }
            title = getString(R.string.planet_title)
        }
        binding.nextButton.setOnClickListener {
            viewModel.getPlanets(viewLifecycleOwner, PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            viewModel.getPlanets(viewLifecycleOwner, PageType.PREVIOUS_PAGE)
        }
        binding.retryButton.setOnClickListener {
            viewModel.getPlanets(viewLifecycleOwner, PageType.CURRENT_PAGE)
        }
        viewModel.getPlanets(viewLifecycleOwner, PageType.FIRST_PAGE)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.planetList.observe(viewLifecycleOwner) { planet ->
            binding.planetRecyclerView.isVisible = true
            binding.previousButton.isEnabled = planet.previous != null
            binding.nextButton.isEnabled = planet.next != null
            binding.planetRecyclerView.adapter = PlanetAdapter(
                planetList = planet.results
            ) {
                val action = PlanetListFragmentDirections.actionNavPlanetToNavPlanetDetail(it)
                findNavController().navigate(action)
            }
        }
        viewModel.failureMessage.observe(viewLifecycleOwner) { failureMessage ->
            binding.planetRecyclerView.adapter = null
            binding.planetRecyclerView.isVisible = false
            binding.errorText.isVisible = true
            binding.errorText.text = failureMessage
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.errorText.isVisible = isLoading
            binding.planetRecyclerView.isVisible = !isLoading
            binding.previousButton.isVisible = !isLoading
            binding.nextButton.isVisible = !isLoading
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}