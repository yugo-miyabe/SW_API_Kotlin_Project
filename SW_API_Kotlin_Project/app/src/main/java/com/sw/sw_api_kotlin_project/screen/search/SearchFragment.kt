package com.sw.sw_api_kotlin_project.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentSearchBinding
import com.sw.sw_api_kotlin_project.model.entity.ListType
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 検索画面
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment() {
    private val viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = checkNotNull(_binding)

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
        binding.searchAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_search)
        binding.searchButton.setOnClickListener {
            viewModel.getSearchResult(binding.searchBar.text.toString())
        }
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.searchResultList.observe(viewLifecycleOwner) { searchResult ->
            if (searchResult[ListType.PEOPLE.ordinal].count != 0 && searchResult[ListType.FILM.ordinal].count != 0 && searchResult[ListType.PLANETS.ordinal].count != 0) {
                binding.searchResultMessage.isVisible = false
                val adapter = SearchResultsAdapter(
                    searchResults = searchResult!!,
                    onPeopleClick = {
                        val action = SearchFragmentDirections.actionNavSearchToNavPeopleDetail(it)
                        findNavController().navigate(action)
                    },
                    onFilmClick = {
                        val action = SearchFragmentDirections.actionNavSearchToNavFilmsDetail(it)
                        findNavController().navigate(action)
                    },
                    onPlanetClick = {
                        val action = SearchFragmentDirections.actionNavSearchToNavPlanetDetail(it)
                        findNavController().navigate(action)
                    },
                )
                binding.bindAdapter(searchResultsAdapter = adapter)
            } else {
                binding.searchResultMessage.isVisible = true
                binding.searchResultMessage.text = getString(R.string.search_result_does_not)
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.searchResultMessage.isVisible = !isLoading
            binding.searchResultRecyclerView.isVisible = !isLoading
            binding.searchButton.isEnabled = !isLoading
            binding.searchResultRecyclerView.adapter = null
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMassage ->
            binding.searchResultMessage.isVisible = true
            binding.searchResultMessage.text = errorMassage
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun FragmentSearchBinding.bindAdapter(searchResultsAdapter: SearchResultsAdapter) {
        searchResultRecyclerView.adapter = searchResultsAdapter
    }
}
