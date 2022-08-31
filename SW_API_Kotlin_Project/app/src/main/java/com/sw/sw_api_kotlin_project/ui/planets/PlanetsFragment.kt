package com.sw.sw_api_kotlin_project.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.PlanetsAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Planet
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetsBinding
import com.sw.sw_api_kotlin_project.repository.PlanetsRepository
import com.sw.sw_api_kotlin_project.utils.PageType


class PlanetsFragment : BaseFragment() {

    private lateinit var viewModel: PlanetsViewModel
    private var _binding: FragmentPlanetsBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PlanetsViewModelFactory(PlanetsRepository(SWServiceClient.getService()))
        )[PlanetsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.planetsNextButton.setOnClickListener {
            getPlanets(PageType.NEXT_PAGE)
        }
        binding.planetsPreviousButton.setOnClickListener {
            getPlanets(PageType.PREVIOUS_PAGE)
        }
        getPlanets(PageType.FIRST_PAGE)
    }

    private fun getPlanets(pageType: PageType) {
        val planetsObserver = object : SWApiLiveDataObserver<Results<Planet>>() {
            override fun onSuccess(data: Results<Planet>?) {
                val planets = data!!
                binding.progressBar.visibility = View.GONE
                binding.planetsRecycler.visibility = View.VISIBLE
                binding.planetsPreviousButton.visibility = View.VISIBLE
                binding.planetsNextButton.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.planetsPreviousButton.isEnabled = planets.previous != null
                binding.planetsNextButton.isEnabled = planets.next != null
                val adapter = PlanetsAdapter(planets.results)
                binding.planetsRecycler.adapter = adapter
                binding.planetsRecycler.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.planetsPreviousButton.visibility = View.GONE
                binding.planetsNextButton.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.errorText.text = errorMessage
                //　TODO 再試行ボタン追加
            }

            override fun onLoading() {
                super.onLoading()
                binding.planetsRecycler.visibility = View.GONE
                binding.planetsPreviousButton.visibility = View.GONE
                binding.planetsNextButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
            }
        }

        viewModel.getPlanets(pageType).observe(viewLifecycleOwner, planetsObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}