package com.sw.sw_api_kotlin_project.activity.planet_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.PlanetAdapter
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.ActivityPlanetListBinding
import com.sw.sw_api_kotlin_project.utils.PageType
import dagger.hilt.android.AndroidEntryPoint


/**
 * 惑星一覧画面
 */
@AndroidEntryPoint
class PlanetListActivity : BaseActivity() {
    private var _binding: ActivityPlanetListBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val viewModel: PlanetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPlanetListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.planetListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                finish()
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
                    /*
                    val action = PlanetListFragmentDirections.actionNavPlanetToNavPlanetDetail(it)
                    findNavController().navigate(action)
                    */
                }
                binding.planetRecycler.adapter = adapter
                binding.planetRecycler.layoutManager = LinearLayoutManager(applicationContext)
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
        viewModel.getPlanets(pageType).observe(this, planetsObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}