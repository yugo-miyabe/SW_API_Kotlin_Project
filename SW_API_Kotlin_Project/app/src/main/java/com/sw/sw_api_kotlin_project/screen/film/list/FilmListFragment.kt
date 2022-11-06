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
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.network.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmListBinding
import com.sw.sw_api_kotlin_project.model.entity.PageType
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
            getFilms(PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            getFilms(PageType.PREVIOUS_PAGE)
        }
        binding.retryButton.setOnClickListener {
            getFilms(PageType.CURRENT_PAGE)
        }
        getFilms(PageType.FIRST_PAGE)
    }

    private fun getFilms(pageType: PageType) {
        val filmObserver = object : SWLiveDataObserver<Results<Film>>() {
            override fun onSuccess(data: Results<Film>?) {
                val films = data!!
                binding.previousButton.isEnabled = films.previous != null
                binding.nextButton.isEnabled = films.next != null
                val adapter = FilmAdapter(films.results) {
                    val action = FilmListFragmentDirections.actionNavFilmsToNavFilmsDetail(it)
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = adapter
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.isVisible = false
                binding.retryButton.isVisible = true
                binding.errorText.isVisible = true
                binding.errorText.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.retryButton.isVisible = false
                binding.errorText.isVisible = false
            }

            override fun onViewChange(shouldListShow: Boolean) {
                super.onViewChange(shouldListShow)
                binding.progressBar.isVisible = !shouldListShow
                binding.recyclerView.isVisible = shouldListShow
                binding.previousButton.isVisible = shouldListShow
                binding.nextButton.isVisible = shouldListShow
            }
        }
        viewModel.getFilms(pageType).observe(viewLifecycleOwner, filmObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}