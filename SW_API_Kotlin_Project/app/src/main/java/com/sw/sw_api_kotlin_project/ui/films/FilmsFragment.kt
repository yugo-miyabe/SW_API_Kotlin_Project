package com.sw.sw_api_kotlin_project.ui.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.FilmsAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.data.model.Films
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsBinding
import com.sw.sw_api_kotlin_project.repository.FilmsRepository

class FilmsFragment : BaseFragment() {
    private lateinit var viewModel: FilmsListViewModel
    private var _binding: FragmentFilmsBinding? = null
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
        _binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getFilms()
    }
    
    private fun getFilms() {
        val filmsObserver = object : SWApiLiveDataObserver<Results<Films>>() {
            override fun onSuccess(data: Results<Films>?) {
                val films = data!!
                binding.progressBar.visibility = View.GONE
                binding.filmRecycler.visibility = View.VISIBLE
                binding.filmPreviousButton.visibility = View.VISIBLE
                binding.filmNextButton.visibility = View.VISIBLE
                binding.filmPreviousButton.isEnabled = films.previous != null
                binding.filmNextButton.isEnabled = films.next != null
                val adapter = FilmsAdapter(films.results)
                binding.filmRecycler.adapter = adapter
                binding.filmRecycler.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.filmPreviousButton.visibility = View.GONE
                binding.filmNextButton.visibility = View.GONE
                binding.errorText.text = errorMessage
                //　TODO 再試行ボタン追加
            }

            override fun onLoading() {
                super.onLoading()
                binding.filmRecycler.visibility = View.GONE
                binding.filmPreviousButton.visibility = View.GONE
                binding.filmNextButton.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        viewModel.getFilms().observe(viewLifecycleOwner, filmsObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}