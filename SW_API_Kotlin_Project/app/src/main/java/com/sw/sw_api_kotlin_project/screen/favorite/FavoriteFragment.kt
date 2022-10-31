package com.sw.sw_api_kotlin_project.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.model.entity.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.model.entity.Favorite
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding
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

    override fun onResume() {
        super.onResume()
        getFavoriteList()
    }

    private fun getFavoriteList() {
        val favoriteListObserver = object : SWLiveDataObserver<List<Favorite>>() {
            override fun onSuccess(data: List<Favorite>?) {
                if (data!!.isNotEmpty()) {
                    binding.favoriteRecyclerView.visibility = View.VISIBLE
                    binding.favoriteRecyclerView.adapter = FavoriteAdapter(
                        data,
                        {
                            val action =
                                FavoriteFragmentDirections.actionNavFavoriteListToNavPeopleDetails(
                                    it
                                )
                            findNavController().navigate(action)
                        },
                        {
                            val action =
                                FavoriteFragmentDirections.actionNavFavoriteListToNavFilmDetails(it)
                            findNavController().navigate(action)

                        },
                        {
                            val action =
                                FavoriteFragmentDirections.actionNavFavoriteListToNavPlanetDetails(
                                    it
                                )
                            findNavController().navigate(action)

                        },
                    )
                    binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)
                } else {
                    binding.favoriteMessage.visibility = View.VISIBLE
                    binding.favoriteMessage.text = getString(R.string.favorite_not_in_favorites)
                }
            }

            override fun onError(errorMessage: String) {
                binding.favoriteMessage.visibility = View.VISIBLE
                binding.favoriteMessage.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.favoriteMessage.visibility = View.GONE
                binding.favoriteRecyclerView.visibility = View.GONE
            }
        }
        viewModel.getFavoriteList().observe(viewLifecycleOwner, favoriteListObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}