package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.FilmsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsBinding
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.FilmsListViewModel
import com.sw.sw_api_kotlin_project.viewmodels.FilmsListViewModelFactory

class FilmsFragment : BaseFragment() {
    private lateinit var viewModel: FilmsListViewModel
    private var _binding: FragmentFilmsBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FilmsListViewModelFactory(APIRepository())
        )[FilmsListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchFilms()
        observeApiLoadingEvent(viewModel)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.films.observe(viewLifecycleOwner) {
            val adapter = FilmsAdapter(it!!.results)
            binding.filmRecycler.adapter = adapter
            binding.filmRecycler.layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}