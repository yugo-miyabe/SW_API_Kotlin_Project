package com.sw.sw_api_kotlin_project.ui.people_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Starships
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleDetailsBinding
import com.sw.sw_api_kotlin_project.repository.StarShipsRepository

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
            PeopleDetailsFactory(StarShipsRepository(swService = SWServiceClient.getService()))
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
        getStarships()
        people = args.people
        people.run {
            binding.fullNameText.text = name
            binding.birthYearText.text = birthYear
            binding.genderText.text = gender
            binding.skinColorText.text = skinColor
            binding.eyeColorText.text = eyeColor
            binding.heightText.text = height
            binding.massText.text = mass
            binding.homeworldText.text = homeWorld
        }
    }

    private fun getStarships() {
        val starShipsObservable = object : SWApiLiveDataObserver<Result<Starships>>() {
            override fun onSuccess(data: Result<Starships>?) {
                TODO("Not yet implemented")
            }

            override fun onError(errorMessage: String) {
                TODO("Not yet implemented")
            }

            override fun onLoading() {
                super.onLoading()
                binding.recyclerViewStarships.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        people.starships.forEach { specise -> viewModel.getStarShips() }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}