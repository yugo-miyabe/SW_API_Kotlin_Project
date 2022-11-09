package com.sw.sw_api_kotlin_project.screen.film.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmListBinding
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.screen.film.FilmAdapter
import dagger.hilt.android.AndroidEntryPoint

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
                activity?.finish()
            }
            title = getString(R.string.film_title)
        }
        binding.nextButton.setOnClickListener {
            viewModel.getFilm(viewLifecycleOwner, PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            viewModel.getFilm(viewLifecycleOwner, PageType.PREVIOUS_PAGE)
        }
        binding.retryButton.setOnClickListener {
            viewModel.getFilm(viewLifecycleOwner, PageType.CURRENT_PAGE)
        }
        viewModel.getFilm(viewLifecycleOwner, PageType.FIRST_PAGE)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.filmList.observe(viewLifecycleOwner) { film ->
            binding.filmRecyclerView.isVisible = true
            binding.previousButton.isEnabled = film.previous != null
            binding.nextButton.isEnabled = film.next != null
            binding.filmRecyclerView.adapter = FilmAdapter(
                filmList = film.results
            ) {
                val action = FilmListFragmentDirections.actionNavFilmsToNavFilmsDetail(it)
                findNavController().navigate(action)
            }
        }
        viewModel.failureMessage.observe(viewLifecycleOwner) { failureMessage ->
            binding.filmRecyclerView.adapter = null
            binding.filmRecyclerView.isVisible = false
            binding.errorText.isVisible = true
            binding.errorText.text = failureMessage
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isVisible ->
            binding.progressBar.isVisible = isVisible
            binding.errorText.isVisible = isVisible
            binding.filmRecyclerView.isVisible = !isVisible
            binding.previousButton.isVisible = !isVisible
            binding.nextButton.isVisible = !isVisible
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}