package com.sw.sw_api_kotlin_project.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.FavoriteAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.Favorite
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            val favoriteList = getRepository()
            if (favoriteList != null) {
                binding.favoriteRecyclerView.adapter = FavoriteAdapter(favoriteList)
                binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)
            } else {
                // TODO お気に入り登録されていません画面追加
            }
        }

    }

    private suspend fun getRepository(): List<Favorite>? {
        return viewModel.getDatabase()
    }


}