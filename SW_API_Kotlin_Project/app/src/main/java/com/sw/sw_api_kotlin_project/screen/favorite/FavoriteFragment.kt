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
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * お気に入り画面
 */
@AndroidEntryPoint
class FavoriteFragment : BaseFragment() {
    private val viewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.favoriteAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_favorite)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteList.observe(viewLifecycleOwner) { list ->
            if (list.isNotEmpty()) {
                binding.favoriteRecyclerView.isVisible = true
                binding.favoriteRecyclerView.adapter = FavoriteAdapter(
                    list,
                    { people ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavPeopleDetails(
                                people
                            )
                        findNavController().navigate(action)
                    },
                    { film ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavFilmDetails(film)
                        findNavController().navigate(action)

                    },
                    { planet ->
                        val action =
                            FavoriteFragmentDirections.actionNavFavoriteListToNavPlanetDetails(
                                planet
                            )
                        findNavController().navigate(action)

                    },
                )
            } else {
                binding.favoriteMessage.visibility = View.VISIBLE
                binding.favoriteMessage.text = getString(R.string.favorite_not_in_favorites)
            }
        }
        viewModel.failureMessage.observe(viewLifecycleOwner) { failureMessage ->
            binding.favoriteRecyclerView.adapter = null
            binding.favoriteRecyclerView.isVisible = false
            binding.favoriteMessage.isVisible = true
            binding.favoriteMessage.text = failureMessage
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isVisible ->
            binding.favoriteMessage.isVisible = isVisible
            binding.favoriteRecyclerView.isVisible = isVisible
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteList(viewLifecycleOwner)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}