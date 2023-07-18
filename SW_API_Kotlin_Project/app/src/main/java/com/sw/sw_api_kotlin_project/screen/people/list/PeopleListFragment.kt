package com.sw.sw_api_kotlin_project.screen.people.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.LoadState
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 登場人物一覧画面
 */
@AndroidEntryPoint
class PeopleListFragment : BaseFragment<PeopleListViewModel, FragmentPeopleListBinding>() {
    override val viewModel: PeopleListViewModel by viewModels()
    override fun inflate(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPeopleListBinding = FragmentPeopleListBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            retryButton.setOnClickListener {
                binding.retryButton.isVisible = false
            }
        }
        val adapter = PeopleListAdapter {
            viewModel.onTapPeople(it)
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
