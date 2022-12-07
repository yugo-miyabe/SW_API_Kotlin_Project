package com.sw.sw_api_kotlin_project.screen.people.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 登場人物一覧画面
 */
@AndroidEntryPoint
class PeopleListFragment : BaseFragment() {
    private val viewModel: PeopleListViewModel by viewModels()
    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.apply {
            peopleListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
                setOnClickListener {
                    activity?.finish()
                }
                title = getString(R.string.people_list_title)
            }
            retryButton.setOnClickListener {
                binding.retryButton.isVisible = false
            }
        }
    }

    override fun addObservers() {
        super.addObservers()

        val adapter = PeopleListAdapter {
            val action = PeopleListFragmentDirections.actionNavPeopleListToNavPeopleDetail(it)
            findNavController().navigate(action)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.peopleItems.collectLatest {
                    adapter.submitData(it)
                }
            }
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collect {
                    binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                }
            }
        }

        binding.peopleRecyclerView.adapter = adapter

        viewModel.failureMessage.observe(viewLifecycleOwner) { failureMessage ->
            binding.peopleRecyclerView.adapter = null
            binding.peopleRecyclerView.isVisible = false
            binding.errorText.isVisible = true
            binding.errorText.text = failureMessage
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.errorText.isVisible = isLoading
            binding.peopleRecyclerView.isVisible = !isLoading
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}