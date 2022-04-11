package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.APIRootAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.viewmodels.APIRootViewModel
import com.sw.sw_api_kotlin_project.viewmodels.APIRootViewModelFactory

class APIRootFragment : BaseFragment() {

    private lateinit var viewModel: APIRootViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            APIRootViewModelFactory(
                APIRepository()
            )
        )[APIRootViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("debug", "テスト")
        viewModel.getAPIRootURL()


    }

    override fun addObservers() {
        super.addObservers()

        viewModel.isAPISuccess.observe(viewLifecycleOwner) {
            if (it) {
                val adapter = APIRootAdapter(viewModel.apiRootURL.value!!)
                val recyclerView = view?.findViewById<RecyclerView>(R.id.home_recycler)
                recyclerView!!.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }
        }
    }


}