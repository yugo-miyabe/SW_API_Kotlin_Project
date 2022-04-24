package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.PeopleAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.PeopleListViewModel
import com.sw.sw_api_kotlin_project.viewmodels.PeopleListViewModelFactory


class PeopleFragment : BaseFragment() {

    private lateinit var viewModel: PeopleListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PeopleListViewModelFactory(
                APIRepository()
            )
        )[PeopleListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_people_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPeopleAPI()
    }


    override fun addObservers() {
        super.addObservers()
        viewModel.people.observe(viewLifecycleOwner) {
            val adapter = PeopleAdapter(it.people)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.people_recycler)
            recyclerView!!.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

}