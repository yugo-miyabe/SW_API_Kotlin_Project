package com.sw.sw_api_kotlin_project.screen.film.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentFilmDetailsBinding
import com.sw.sw_api_kotlin_project.network.model.Film
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.screen.base.FilmNavListener
import com.sw.sw_api_kotlin_project.screen.film.FilmActivity
import dagger.hilt.android.AndroidEntryPoint


/**
 * 映画詳細画面
 */
@AndroidEntryPoint
class FilmDetailsFragment : BaseFragment() {
    private val viewModel: FilmDetailsViewModel by viewModels()
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: FilmDetailsFragmentArgs by navArgs()
    private lateinit var film: Film

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.filmDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                if (activity is FilmActivity) {
                    view.findNavController().navigateUp()
                } else {
                    activity?.finish()
                }
            }
            title = getString(R.string.films_details_title)
        }
        film = if (activity is FilmActivity) {
            args.film
        } else {
            val navListener = activity as FilmNavListener
            navListener.getFilmValue()
        }
        film.run {
            binding.titleText.text = title
            binding.releaseDateText.text = releaseDate
            binding.openingCrawlText.text = openingCrawl
        }
        binding.filmFavoriteMark.setOnClickListener {
            viewModel.toggleFavorite(film)
        }
        viewModel.getFavoriteState(film.title)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteStatus.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
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