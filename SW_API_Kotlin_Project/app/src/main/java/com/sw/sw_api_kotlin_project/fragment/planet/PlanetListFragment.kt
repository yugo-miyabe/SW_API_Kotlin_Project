package com.sw.sw_api_kotlin_project.fragment.planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.PlanetAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetListBinding
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.utils.PageType


/**
 * 惑星一覧画面
 */
class PlanetListFragment : BaseFragment() {
    private lateinit var viewModel: PlanetViewModel
    private var _binding: FragmentPlanetListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PlanetViewModelFactory(PlanetRepository(SWServiceClient.getService()))
        )[PlanetViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.planetListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                view.findNavController().navigateUp()
            }
            title = getString(R.string.planet_title)
        }
        binding.nextButton.setOnClickListener {
            getPlanet(PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            getPlanet(PageType.PREVIOUS_PAGE)
        }
        binding.retryButton.setOnClickListener {
            getPlanet(PageType.CURRENT_PAGE)
        }
        getPlanet(PageType.FIRST_PAGE)
    }

    private fun getPlanet(pageType: PageType) {
        val planetsObserver = object : SWLiveDataObserver<Results<Planet>>() {
            override fun onSuccess(data: Results<Planet>?) {
                val planets = data!!
                binding.previousButton.isEnabled = planets.previous != null
                binding.nextButton.isEnabled = planets.next != null
                val adapter = PlanetAdapter(planets.results) {
                    val action = PlanetListFragmentDirections.actionNavPlanetToNavPlanetDetail(it)
                    findNavController().navigate(action)
                }
                binding.planetRecycler.adapter = adapter
                binding.planetRecycler.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.isVisible = false
                binding.retryButton.isVisible = true
                binding.errorText.isVisible = true
                binding.errorText.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.retryButton.isVisible = false
                binding.errorText.isVisible = false
            }

            override fun onViewChange(shouldListShow: Boolean) {
                super.onViewChange(shouldListShow)
                binding.progressBar.isVisible = !shouldListShow
                binding.planetRecycler.isVisible = shouldListShow
                binding.previousButton.isVisible = shouldListShow
                binding.nextButton.isVisible = shouldListShow
            }
        }
        viewModel.getPlanets(pageType).observe(viewLifecycleOwner, planetsObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}