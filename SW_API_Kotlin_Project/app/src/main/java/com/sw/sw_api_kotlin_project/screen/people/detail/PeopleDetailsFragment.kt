package com.sw.sw_api_kotlin_project.screen.people.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleDetailsBinding
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.screen.base.PeopleNavListener
import dagger.hilt.android.AndroidEntryPoint

/**
 * 登場人物詳細画面
 */
@AndroidEntryPoint
class PeopleDetailsFragment : BaseFragment() {
    private val viewModel: PeopleDetailsViewModel by viewModels()
    private var _binding: FragmentPeopleDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PeopleDetailsFragmentArgs by navArgs()
    private lateinit var people: People

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.peopleDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener {
                findNavController().popBackStack()
            }
            title = getString(R.string.people_details_title)
        }
        /*
        binding.peopleDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                if (activity is PeopleActivity) {
                    view.findNavController().navigateUp()
                } else {
                    activity?.finish()
                }
            }
            title = getString(R.string.people_details_title)
        }
        */
        /*
        people = if (activity is PeopleActivity) {
            args.people
        } else {
            val navListener = activity as PeopleNavListener
            navListener.getPeopleValue()
        }
        */
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
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteStatus.observe(viewLifecycleOwner) { isFavorite ->
            if (isFavorite) {
                binding.peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_24)
            } else {
                binding.peopleFavoriteMark.setImageResource(R.drawable.ic_baseline_star_border_24)
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}