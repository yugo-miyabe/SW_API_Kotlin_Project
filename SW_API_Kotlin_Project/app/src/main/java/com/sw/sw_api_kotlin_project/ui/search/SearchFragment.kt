package com.sw.sw_api_kotlin_project.ui.search

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.SearchResultsAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentSearchBinding
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.repository.PlanetRepository
import com.sw.sw_api_kotlin_project.utils.ListType

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
        val searchResultObserver = object : SWLiveDataObserver<List<Results<out Parcelable>>>() {
            override fun onSuccess(data: List<Results<out Parcelable>>?) {
                binding.progressBar.visibility = View.GONE
                val adapter = SearchResultsAdapter(data!!, {}, {}, {})
                binding.searchResultRecyclerView.adapter = adapter
                binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(context)
                binding.searchButton.isEnabled = true
                binding.searchResultRecyclerView.visibility = View.VISIBLE
                if (data[ListType.PEOPLE.ordinal].count == 0 && data[ListType.FILM.ordinal].count == 0 && data[ListType.PLANETS.ordinal].count == 0)
                    binding.searchResultDoesNot.visibility = View.VISIBLE
            }

            override fun onError(errorMessage: String) {
                binding.searchButton.isEnabled = true
                binding.progressBar.visibility = View.GONE
                binding.searchResultDoesNot.visibility = View.GONE
            }

            override fun onLoading() {
                super.onLoading()
                binding.searchButton.isEnabled = false
                binding.progressBar.visibility = View.VISIBLE
                binding.searchResultRecyclerView.visibility = View.GONE
                binding.searchResultDoesNot.visibility = View.GONE
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