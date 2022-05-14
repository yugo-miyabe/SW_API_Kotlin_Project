package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.StarShipsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentStarshipsBinding
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.StarShipsViewModel
import com.sw.sw_api_kotlin_project.viewmodels.StarShipsViewModelFactory

class StarShipsFragment : BaseFragment() {
    private lateinit var viewModel: StarShipsViewModel
    private var _binding: FragmentStarshipsBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            StarShipsViewModelFactory(APIRepository())
        )[StarShipsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStarshipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStarShipsAPI()
        observeApiLoadingEvent(viewModel)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.starShips.observe(viewLifecycleOwner) {
            val adapter = StarShipsAdapter(it.starships)
            binding.starshipsRecycler.adapter = adapter
            binding.starshipsRecycler.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}