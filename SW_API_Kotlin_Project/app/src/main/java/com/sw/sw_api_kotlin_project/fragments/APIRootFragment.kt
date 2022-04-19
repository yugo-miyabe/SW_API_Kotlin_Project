package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        viewModel.getAPIRootURL()
    }

    override fun addObservers() {
        super.addObservers()

        viewModel.homeData.observe(viewLifecycleOwner) {
            val adapter = APIRootAdapter(it) { fragment ->
                adapterOnClick(fragment)
            }
            val recyclerView = view?.findViewById<RecyclerView>(R.id.home_recycler)
            recyclerView!!.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun adapterOnClick(fragment: Fragment) {
        //　TODO キャスト対応
        val activity: AppCompatActivity = context as AppCompatActivity
        activity.supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }

}