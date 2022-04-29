package com.sw.sw_api_kotlin_project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapters.APIRootAdapter
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.databinding.FragmentHomeBinding
import com.sw.sw_api_kotlin_project.repository.APIRepository
import com.sw.sw_api_kotlin_project.utils.Constants
import com.sw.sw_api_kotlin_project.viewmodels.APIRootViewModel
import com.sw.sw_api_kotlin_project.viewmodels.APIRootViewModelFactory

class APIRootFragment : BaseFragment() {

    private lateinit var viewModel: APIRootViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            APIRootViewModelFactory(
                APIRepository()
            )
        )[APIRootViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAPIRootURL()
    }

    override fun addObservers() {
        super.addObservers()

        viewModel.apiRootURL.observe(viewLifecycleOwner) {
            val adapter = APIRootAdapter(it) { position ->
                adapterOnClick(position)
            }
            binding.homeRecycler.adapter = adapter
            binding.homeRecycler.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun adapterOnClick(position: Int) {
        val fragment = getTransitionFragment(Constants.Root.values()[position])
        val activity: AppCompatActivity = context as AppCompatActivity
        activity.supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, fragment)
            .commit()
    }


    private fun getTransitionFragment(item: Constants.Root): Fragment {
        when (item) {
            Constants.Root.People -> {
                return PeopleFragment()
            }
            Constants.Root.Planets -> {
                return PlanetsFragment()
            }
            Constants.Root.Films -> {
                return FilmsFragment()
            }
            Constants.Root.Species -> {
                return SpeciesFragment()
            }
            Constants.Root.Vehicles -> {
                return VehiclesFragment()
            }
            Constants.Root.StarShips -> {
                return StarShipsFragment()
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}