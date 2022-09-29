package com.sw.sw_api_kotlin_project.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.HomeAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.HomeItem
import com.sw.sw_api_kotlin_project.databinding.FragmentHomeBinding
import com.sw.sw_api_kotlin_project.utils.ListType

/**
 * ホーム画面
 */
class HomeFragment : BaseFragment() {
    private lateinit var viewModel: HomeViewModel
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
        val adapter = HomeAdapter(getHomeList()) {
            val action = when (it) {
                ListType.PEOPLE -> {
                    HomeFragmentDirections.actionNavHomeListToNavPeopleList()
                }
                ListType.FILM -> {
                    HomeFragmentDirections.actionNavHomeListToNavFilmsList()
                }
                ListType.PLANETS -> {
                    HomeFragmentDirections.actionNavHomeListToNavPlanetList()
                }
            }
            findNavController().navigate(action)
        }
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    private fun getHomeList(): List<HomeItem> {
        return listOf(
            HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_face_24),
                getString(R.string.people),
                ListType.PEOPLE
            ), HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_film_24),
                getString(R.string.films),
                ListType.FILM
            ), HomeItem(
                getDrawable(requireContext(), R.drawable.ic_baseline_planets_24),
                getString(R.string.planet),
                ListType.PLANETS
            )
        )
    }
}