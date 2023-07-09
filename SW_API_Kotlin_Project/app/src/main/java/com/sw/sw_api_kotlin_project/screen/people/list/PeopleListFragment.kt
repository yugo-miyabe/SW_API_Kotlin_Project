package com.sw.sw_api_kotlin_project.screen.people.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragmentTest
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 登場人物一覧画面
 */
@AndroidEntryPoint
class PeopleListFragment : BaseFragmentTest<PeopleListViewModel, FragmentPeopleListBinding>() {
    override val viewModel: PeopleListViewModel by viewModels()
    override fun inflate(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPeopleListBinding = FragmentPeopleListBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            peopleListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
                setOnClickListener {
                    findNavController().popBackStack()
                }
                title = getString(R.string.people_list_title)
            }
            retryButton.setOnClickListener {
                binding.retryButton.isVisible = false
            }
        }
        val adapter = PeopleListAdapter {
            val action = PeopleListFragmentDirections.actionNavPeopleListToNavPeopleDetail(it)
            findNavController().navigate(action)
        }

        viewModel.viewModelScope.launch {
            viewModel.peopleItems.collectLatest {
                adapter.submitData(it)
            }
        }
        
        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                binding.appendProgress.isVisible = loadStates.source.append is LoadState.Loading
                val loadStateRefresh: LoadState = loadStates.refresh
                binding.progressBar.isVisible = loadStateRefresh is LoadState.Loading
                binding.errorText.isVisible = loadStateRefresh is LoadState.Error
                binding.retryButton.isVisible = loadStateRefresh is LoadState.Error
                if (loadStateRefresh is LoadState.Error) binding.errorText.text =
                    loadStateRefresh.error.localizedMessage
            }
        }

        binding.retryButton.setOnClickListener {
            adapter.retry()
        }

        binding.peopleRecyclerView.adapter = adapter

    }

}
