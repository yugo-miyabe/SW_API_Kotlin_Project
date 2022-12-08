package com.sw.sw_api_kotlin_project.screen.film.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmListBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 映画一覧画面
 */
@AndroidEntryPoint
class FilmListFragment : BaseFragment() {
    private val viewModel: FilmListViewModel by viewModels()
    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.filmListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                findNavController().popBackStack()
            }
            title = getString(R.string.film_title)
        }
    }

    override fun addObservers() {
        super.addObservers()
        val adapter = FilmListAdapter {
            val action = FilmListFragmentDirections.actionNavFilmsToNavFilmsDetail(it)
            findNavController().navigate(action)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filmItems.collectLatest {
                    adapter.submitData(it)
                }
            }
        }

        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                binding.appendProgress.isVisible = loadStates.source.append is LoadState.Loading
                binding.errorText.isVisible = loadStates.refresh is LoadState.Error
            }
        }

        binding.filmRecyclerView.adapter = adapter

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}