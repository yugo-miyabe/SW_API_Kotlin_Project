package com.sw.sw_api_kotlin_project.activity.films_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.FilmsAdapter
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.ActivityFilmsListBinding
import com.sw.sw_api_kotlin_project.utils.PageType
import dagger.hilt.android.AndroidEntryPoint

/**
 * 映画一覧画面
 */
@AndroidEntryPoint
class FilmsListActivity : BaseActivity() {
    private var _binding: ActivityFilmsListBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val viewModel: FilmsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFilmsListBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    // TODO 画面遷移

                }
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)
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
        viewModel.getFilms(pageType).observe(this, filmObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}