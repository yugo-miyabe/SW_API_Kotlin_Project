package com.sw.sw_api_kotlin_project.ui.people_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleDetailsBinding


class PeopleDetailsFragment : BaseFragment() {
    private lateinit var viewModel: PeopleDetailsViewModel
    private var _binding: FragmentPeopleDetailsBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val args: PeopleDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PeopleDetailsFactory()
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
        val people :People = args.people
        binding.fullNameText.text = people.name

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}