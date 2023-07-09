package com.sw.sw_api_kotlin_project.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.entity.ListType
import com.sw.sw_api_kotlin_project.databinding.FragmentSearchBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 検索画面
 */
@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {
    override val viewModel: SearchViewModel by viewModels()
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentSearchBinding =
        FragmentSearchBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_search)
        binding.searchButton.setOnClickListener {
            viewModel.getSearchResult(binding.searchBar.text.toString())
        }

        viewModel.searchResultList.observe(viewLifecycleOwner) { searchResult ->
            if (searchResult[ListType.PEOPLE.ordinal].count != 0 && searchResult[ListType.FILM.ordinal].count != 0 && searchResult[ListType.PLANETS.ordinal].count != 0) {
                binding.searchResultMessage.isVisible = false
                val adapter = SearchResultsAdapter(
                    searchResults = searchResult!!,
                    onPeopleClick = { people ->
                        viewModel.onTapPeople(people)
                    },
                    onFilmClick = { film ->
                        viewModel.onTapFilm(film)
                    },
                    onPlanetClick = { planet ->
                        viewModel.onTapPlanet(planet)
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

    private fun FragmentSearchBinding.bindAdapter(searchResultsAdapter: SearchResultsAdapter) {
        searchResultRecyclerView.adapter = searchResultsAdapter
    }
}
