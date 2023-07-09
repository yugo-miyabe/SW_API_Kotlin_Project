package com.sw.sw_api_kotlin_project.screen.people.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleDetailsBinding
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * 登場人物詳細画面
 */
@AndroidEntryPoint
class PeopleDetailsFragment :
    BaseFragment<PeopleDetailsViewModel, FragmentPeopleDetailsBinding>() {
    override val viewModel: PeopleDetailsViewModel by viewModels()
    private val args: PeopleDetailsFragmentArgs by navArgs()
    private lateinit var people: People

    override fun inflate(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentPeopleDetailsBinding =
        FragmentPeopleDetailsBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.peopleDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                findNavController().popBackStack()
            }
            title = getString(R.string.people_details_title)
        }
        people = args.people
        people.run {
            binding.fullNameText.text = name
            binding.birthYearText.text = birthYear
            binding.genderText.text = gender
            binding.skinColorText.text = skinColor
            binding.eyeColorText.text = eyeColor
            binding.heightText.text = height
            binding.hairColorText.text = hairColor
            binding.massText.text = mass
        }
        binding.peopleFavoriteMark.setOnClickListener {
            viewModel.toggleFavorite(people)
        }
        viewModel.getFavoriteState(people.name)
        viewModel.favoriteStatus.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }
}
