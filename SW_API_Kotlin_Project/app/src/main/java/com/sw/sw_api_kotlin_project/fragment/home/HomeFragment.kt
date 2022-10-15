package com.sw.sw_api_kotlin_project.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.HomeAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.HomeItem
import com.sw.sw_api_kotlin_project.databinding.FragmentHomeBinding
import com.sw.sw_api_kotlin_project.utils.ListType
import dagger.hilt.android.AndroidEntryPoint

/**
 * ホーム画面
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel by viewModels<HomeViewModel> { HomeFactory() }
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.homeAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_home)
        val adapter = HomeAdapter(getHomeList()) {
            when (it) {
                ListType.PEOPLE -> {
                    val action = HomeFragmentDirections.actionNavHomeListToNavPeopleListActivity()
                    findNavController().navigate(action)
                }
                ListType.FILM -> {
                    val action = HomeFragmentDirections.actionNavHomeListToNavFilmsList()
                    findNavController().navigate(action)

                }
                ListType.PLANETS -> {
                    val action = HomeFragmentDirections.actionNavHomeListToNavPlanetList()
                    findNavController().navigate(action)

                }
            }
        }
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    private fun getHomeList(): List<HomeItem> {
        return listOf(
            HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_face_24),
                getString(R.string.home_people),
                ListType.PEOPLE
            ), HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_film_24),
                getString(R.string.home_films),
                ListType.FILM
            ), HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_planets_24),
                getString(R.string.home_planet),
                ListType.PLANETS
            )
        )
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}