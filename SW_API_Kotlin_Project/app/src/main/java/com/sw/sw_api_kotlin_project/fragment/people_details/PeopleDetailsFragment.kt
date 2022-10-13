package com.sw.sw_api_kotlin_project.fragment.people_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleDetailsBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import dagger.hilt.android.AndroidEntryPoint

/**
 * 登場人物詳細画面
 */
@AndroidEntryPoint
class PeopleDetailsFragment : BaseFragment() {
    private lateinit var viewModel: PeopleDetailsViewModel
    private var _binding: FragmentPeopleDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PeopleDetailsFragmentArgs by navArgs()
    private lateinit var people: People

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PeopleDetailsFactory(
                FavoriteRepository(
                    FavoriteDatabase.getDatabase(activity?.application!!).favoriteDao()
                ),
            ),
        )[PeopleDetailsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.peopleDetailAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
            setOnClickListener { view ->
                view.findNavController().navigateUp()
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
            viewModel.addOrDeleteFavorite(people)
        }
        viewModel.getFavoriteState(people.name)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.favoriteStatus.observe(viewLifecycleOwner) {
            if (it) {
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