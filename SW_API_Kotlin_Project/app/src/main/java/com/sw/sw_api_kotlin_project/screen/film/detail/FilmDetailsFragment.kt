package com.sw.sw_api_kotlin_project.screen.film.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmDetailsBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * 映画詳細画面
 */
@AndroidEntryPoint
class FilmDetailsFragment : BaseFragment<FilmDetailsViewModel, FragmentFilmDetailsBinding>() {
    override val viewModel: FilmDetailsViewModel by viewModels()
    private val args: FilmDetailsFragmentArgs by navArgs()
    private lateinit var film: Film

    override fun inflate(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentFilmDetailsBinding = FragmentFilmDetailsBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        film = args.film
        film.run {
            binding.titleText.text = title
            binding.releaseDateText.text = releaseDate
            binding.openingCrawlText.text = openingCrawl
        }

        binding.filmFavoriteMark.setOnClickListener {
            viewModel.toggleFavorite(film)
        }

        viewModel.getFavoriteState(film.title)

        viewModel.favoriteStatus.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.filmFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.filmFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

}
