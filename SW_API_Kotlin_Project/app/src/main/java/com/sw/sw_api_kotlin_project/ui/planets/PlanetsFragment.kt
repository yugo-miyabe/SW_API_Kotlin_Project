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
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.repository.PlanetsRepository
import com.sw.sw_api_kotlin_project.utils.PageType


class PlanetsFragment : BaseFragment() {

    private lateinit var viewModel: PlanetsViewModel
    private var _binding: FragmentPeopleListBinding? = null
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
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.nextButton.setOnClickListener {
            getPlanets(PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            getPlanets(PageType.PREVIOUS_PAGE)
        }
        getPlanets(PageType.FIRST_PAGE)
    }

    private fun getPlanets(pageType: PageType) {
        val planetsObserver = object : SWApiLiveDataObserver<Results<Planet>>() {
            override fun onSuccess(data: Results<Planet>?) {
                val planets = data!!
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.previousButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.previousButton.isEnabled = planets.previous != null
                binding.nextButton.isEnabled = planets.next != null
                val adapter = PlanetsAdapter(planets.results)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.errorText.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.recyclerView.visibility = View.GONE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
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