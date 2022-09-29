package com.sw.sw_api_kotlin_project.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentSearchBinding
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository

/**
 * 検索画面
 */
class SearchFragment : BaseFragment() {
    private lateinit var viewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(
                PeopleRepository(SWServiceClient.getService()),
                FilmsRepository(SWServiceClient.getService()),
                PlanetRepository(SWServiceClient.getService()),
            )
        )[SearchViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()

        binding.searchButton.setOnClickListener {
            val searchString = binding.searchBar.text.toString()
            getSearchResult(searchString)
        }
    }

    private fun getSearchResult(searchString: String) {
        val searchResultObserver = object : SWApiLiveDataObserver<Results<People>>() {

            override fun onSuccess(data: Results<People>?) {
                val hoge = data
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }

            override fun onLoading() {
                super.onLoading()
                TODO("Not yet implemented")
            }

        }
        viewModel.getSearchResult(searchString = searchString)
            .observe(viewLifecycleOwner, searchResultObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}