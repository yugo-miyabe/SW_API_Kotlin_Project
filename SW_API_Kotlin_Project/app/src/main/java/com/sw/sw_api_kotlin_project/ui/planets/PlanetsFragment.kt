package com.sw.sw_api_kotlin_project.ui.planets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.PlanetsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetsBinding
import com.sw.sw_api_kotlin_project.repository.APIRepository


class PlanetsFragment : BaseFragment() {

    private lateinit var viewModel: PlanetsViewModel
    private var _binding: FragmentPlanetsBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PlanetsViewModelFactory(
                APIRepository()
            )
        )[PlanetsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanetsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchPlanets()
        observeApiLoadingEvent(viewModel)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.planet.observe(viewLifecycleOwner) {
            val adapter = PlanetsAdapter(it!!.results)
            binding.planetsRecycler.adapter = adapter
            binding.planetsRecycler.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


}