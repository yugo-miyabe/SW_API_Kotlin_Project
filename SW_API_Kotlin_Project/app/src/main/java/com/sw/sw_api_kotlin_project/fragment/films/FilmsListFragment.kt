package com.sw.sw_api_kotlin_project.fragment.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.FilmsAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsListBinding
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.utils.PageType

/**
 * 映画一覧画面
 */
class FilmsListFragment : BaseFragment() {
    private lateinit var viewModel: FilmsListViewModel
    private var _binding: FragmentFilmsListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FilmsListViewModelFactory(FilmsRepository(SWServiceClient.getService()))
        )[FilmsListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.filmListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                view.findNavController().navigateUp()
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
                val adapter = FilmsAdapter(films.results) {
                    val action = FilmsListFragmentDirections.actionNavFilmsToNavFilmsDetail(it)
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
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