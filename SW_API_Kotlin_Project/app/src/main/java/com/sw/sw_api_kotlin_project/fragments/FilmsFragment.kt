package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.FilmsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.FilmsListViewModel
import com.sw.sw_api_kotlin_project.viewmodels.FilmsListViewModelFactory

class FilmsFragment : BaseFragment() {

    private lateinit var viewModel: FilmsListViewModel

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
    ): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFilmsAPI()
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.films.observe(viewLifecycleOwner) {
            val adapter = FilmsAdapter(it.films)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.film_recycler)
            recyclerView!!.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

}