package com.sw.sw_api_kotlin_project.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.FavoriteAdapter
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository

/**
 * お気に入り画面
 */
class FavoriteFragment : BaseFragment() {
    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            FavoriteFactory(
                FavoriteRepository(
                    FavoriteDatabase.getDatabase(activity?.application!!).FavoriteDao()
                )
            )
        )[FavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.favoriteAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_favorite)
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
                                FavoriteFragmentDirections.actionNavFavoriteListToNavPeopleDetail(it)
                            findNavController().navigate(action)
                        },
                        {
                            val action =
                                FavoriteFragmentDirections.actionNavFavoriteListToNavFilmsDetail(it)
                            findNavController().navigate(action)

                        },
                        {
                            val action =
                                FavoriteFragmentDirections.actionNavFavoriteListToNavPlanetDetail(it)
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