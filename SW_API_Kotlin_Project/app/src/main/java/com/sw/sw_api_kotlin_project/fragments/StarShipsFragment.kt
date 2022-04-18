package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.StarShipsAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.StarShipsViewModel
import com.sw.sw_api_kotlin_project.viewmodels.StarShipsViewModelFactory

class StarShipsFragment : BaseFragment() {
    private lateinit var viewModel: StarShipsViewModel

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
    ): View? {
        return inflater.inflate(R.layout.fragment_starships, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getStarShipsAPI()
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.starShips.observe(viewLifecycleOwner) {
            val adapter = StarShipsAdapter(it.starships)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.starships_recycler)
            recyclerView!!.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }
}