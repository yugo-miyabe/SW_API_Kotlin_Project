package com.sw.sw_api_kotlin_project.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.model.entity.HomeItem
import com.sw.sw_api_kotlin_project.data.model.entity.ListType
import com.sw.sw_api_kotlin_project.databinding.FragmentHomeBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * ホーム画面
 */
@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeAppbar.findViewById<MaterialToolbar>(R.id.toolbar).title =
            getString(R.string.navigation_home)
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

    }

    private fun getHomeList(): List<HomeItem> {
        return listOf(
            HomeItem(
                image = getDrawable(requireContext(), R.drawable.ic_baseline_face_24),
                title = getString(R.string.home_people),
                listType = ListType.PEOPLE
            ), HomeItem(
                image = getDrawable(requireContext(), R.drawable.ic_baseline_film_24),
                title = getString(R.string.home_film),
                listType = ListType.FILM
            ), HomeItem(
                image = getDrawable(requireContext(), R.drawable.ic_baseline_planets_24),
                title = getString(R.string.home_planet),
                listType = ListType.PLANETS
            )
        )
    }

}
