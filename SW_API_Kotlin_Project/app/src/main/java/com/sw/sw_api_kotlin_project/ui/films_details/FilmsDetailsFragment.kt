package com.sw.sw_api_kotlin_project.ui.films_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsDetailsBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import kotlinx.coroutines.launch

/**
 * 映画詳細画面
 */
class FilmsDetailsFragment : BaseFragment() {
    private lateinit var viewModel: FilmsDetailsViewModel
    private var _binding: FragmentFilmsDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: FilmsDetailsFragmentArgs by navArgs()
    private lateinit var film: Film

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this, FilmsDetailsFactory(
                    FavoriteRepository(
                        FavoriteDatabase.getDatabase(activity?.application!!).FavoriteDao()
                    ),
                )
            )[FilmsDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        film = args.films
        film.run {
            binding.titleText.text = title
            binding.releaseDateText.text = releaseDate
            binding.openingCrawlText.text = openingCrawl
        }
        binding.filmFavoriteMark.setOnClickListener {
            lifecycleScope.launch {
                viewModel.addOrDeleteFavorite(film)
            }
        }
        lifecycleScope.launch {
            viewModel.getFavoriteState(film.title)
        }
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteStatus.observe(viewLifecycleOwner) {
            if (it) {
                binding.filmFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.filmFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}