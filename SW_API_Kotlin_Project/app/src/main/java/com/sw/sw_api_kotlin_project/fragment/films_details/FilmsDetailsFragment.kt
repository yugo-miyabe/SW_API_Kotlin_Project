package com.sw.sw_api_kotlin_project.fragment.films_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.data.model.Film
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmsDetailsBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository

/**
 * 映画詳細画面
 */
class FilmsDetailsFragment : BaseFragment() {
    private val viewModel by viewModels<FilmsDetailsViewModel> {
        FilmsDetailsFactory(
            FavoriteRepository(FavoriteDatabase.getDatabase(activity?.application!!).favoriteDao())
        )
    }
    private var _binding: FragmentFilmsDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: FilmsDetailsFragmentArgs by navArgs()
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.filmDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                view.findNavController().navigateUp()
            }
            title = getString(R.string.films_details_title)
        }
        film = args.films
        film.run {
            binding.titleText.text = title
            binding.releaseDateText.text = releaseDate
            binding.openingCrawlText.text = openingCrawl
        }
        binding.filmFavoriteMark.setOnClickListener {
            viewModel.addOrDeleteFavorite(film)
        }
        viewModel.getFavoriteState(film.title)
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