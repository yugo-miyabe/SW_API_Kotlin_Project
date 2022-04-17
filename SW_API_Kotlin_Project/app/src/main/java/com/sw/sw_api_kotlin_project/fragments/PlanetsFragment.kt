package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.PlanetsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.PlanetsViewModel
import com.sw.sw_api_kotlin_project.viewmodels.PlanetsViewModelFactory


class PlanetsFragment : BaseFragment() {

    private lateinit var viewModel: PlanetsViewModel

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
    ): View? {
        return inflater.inflate(R.layout.fragment_planets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPlanetsAPI()
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.planet.observe(viewLifecycleOwner) {
            val adapter = PlanetsAdapter(it.planets)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.planets_recycler)
            recyclerView!!.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }


}