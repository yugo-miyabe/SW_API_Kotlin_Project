package com.sw.sw_api_kotlin_project.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.FilmsAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.Films
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsListBinding
import com.sw.sw_api_kotlin_project.repository.FilmsRepository
import com.sw.sw_api_kotlin_project.utils.PageType

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
        binding.nextButton.setOnClickListener {
            getFilms(PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            getFilms(PageType.PREVIOUS_PAGE)
        }
        getFilms(PageType.FIRST_PAGE)
    }

    private fun getFilms(pageType: PageType) {
        val filmsObserver = object : SWApiLiveDataObserver<Results<Films>>() {
            override fun onSuccess(data: Results<Films>?) {
                val films = data!!
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.previousButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
                binding.previousButton.isEnabled = films.previous != null
                binding.nextButton.isEnabled = films.next != null
                val adapter = FilmsAdapter(films.results)
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
                binding.errorText.text = errorMessage
                binding.retryButton.visibility = View.VISIBLE
            }

            override fun onLoading() {
                super.onLoading()
                binding.recyclerView.visibility = View.GONE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
            }
        }
        viewModel.getFilms(pageType).observe(viewLifecycleOwner, filmsObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}