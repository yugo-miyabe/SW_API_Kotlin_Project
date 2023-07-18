package com.sw.sw_api_kotlin_project.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * お気に入り画面
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>() {
    override val viewModel: FavoriteViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList.isNotEmpty()) {
                binding.favoriteMessage.isVisible = false
                binding.favoriteRecyclerView.isVisible = true
                binding.favoriteRecyclerView.adapter = FavoriteAdapter(
                    favoriteList,
                    { people -> viewModel.onTapPeople(people) },
                    { film -> viewModel.onTapFilm(film) },
                    { planet -> viewModel.onTapPlanet(planet) },
                )
            } else {
                binding.favoriteRecyclerView.isVisible = false
                binding.favoriteMessage.isVisible = true
                binding.favoriteMessage.text = getString(R.string.favorite_not_in_favorites)
            }
        }

        viewModel.favoriteMessage.observe(viewLifecycleOwner) { message ->
            binding.favoriteMessage.text = message
        }
    }
}
