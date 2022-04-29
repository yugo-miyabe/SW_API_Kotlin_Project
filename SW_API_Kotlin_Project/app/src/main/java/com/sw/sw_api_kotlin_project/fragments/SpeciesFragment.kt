package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.SpeciesAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentSpeciesBinding
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.SpeciesViewModel
import com.sw.sw_api_kotlin_project.viewmodels.SpeciesViewModelFactory

class SpeciesFragment : BaseFragment() {

    private lateinit var viewModel: SpeciesViewModel
    private var _binding: FragmentSpeciesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SpeciesViewModelFactory(
                APIRepository()
            )
        )[SpeciesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSpeciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSpeciesAPI()
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.species.observe(viewLifecycleOwner) {
            val adapter = SpeciesAdapter(it.species)
            binding.speciesRecycler.adapter = adapter
            binding.speciesRecycler.layoutManager = LinearLayoutManager(context)
        }

    }

}