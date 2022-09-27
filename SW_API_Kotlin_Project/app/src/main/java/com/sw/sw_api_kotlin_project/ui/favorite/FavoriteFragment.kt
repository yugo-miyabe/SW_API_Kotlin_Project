package com.sw.sw_api_kotlin_project.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment() {
    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

}