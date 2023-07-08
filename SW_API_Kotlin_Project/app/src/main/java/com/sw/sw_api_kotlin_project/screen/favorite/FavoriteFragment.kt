package com.sw.sw_api_kotlin_project.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragmentTest
import dagger.hilt.android.AndroidEntryPoint

/**
 * お気に入り画面
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragmentTest<FavoriteViewModel, FragmentFavoriteBinding>() {
    override val viewModel: FavoriteViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentFavoriteBinding =
        FragmentFavoriteBinding.inflate(inflater, container, false)
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favoriteAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_favorite)

        viewModel.favoriteList.observe(viewLifecycleOwner) { favoriteList ->
            if (favoriteList.isNotEmpty()) {
                binding.favoriteMessage.isVisible = false
                binding.favoriteRecyclerView.isVisible = true
                binding.favoriteRecyclerView.adapter = FavoriteAdapter(
                    favoriteList,
                    { people ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavPeopleDetail(
                                people
                            )
                        findNavController().navigate(action)
                    },
                    { film ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavFilmsDetail(film)
                        findNavController().navigate(action)
                    },
                    { planet ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavPlanetDetail(
                                planet
                            )
                        findNavController().navigate(action)
                    },
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
