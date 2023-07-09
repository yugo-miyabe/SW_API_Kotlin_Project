package com.sw.sw_api_kotlin_project.screen.planet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPlanetListBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * 惑星一覧画面
 */
@AndroidEntryPoint
class PlanetListFragment : BaseFragment<PlanetViewModel, FragmentPlanetListBinding>() {
    override val viewModel: PlanetViewModel by viewModels()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlanetListBinding = FragmentPlanetListBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.planetListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                findNavController().popBackStack()
            }
            title = getString(R.string.planet_title)
        }


        val adapter = PlanetListAdapter {
            viewModel.onTapPlanet(it)
        }

        viewModel.viewModelScope.launch {
            viewModel.planetItems.collect {
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
                if (loadStateRefresh is LoadState.Error)
                    binding.errorText.text = loadStateRefresh.error.localizedMessage
            }
        }
        binding.retryButton.setOnClickListener {
            adapter.retry()
        }
        binding.planetRecyclerView.adapter = adapter
    }
}
