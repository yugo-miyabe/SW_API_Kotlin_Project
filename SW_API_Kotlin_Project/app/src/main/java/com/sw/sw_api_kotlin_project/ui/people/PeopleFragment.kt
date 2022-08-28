package com.sw.sw_api_kotlin_project.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.PeopleAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.repository.PeopleRepository

class PeopleFragment : BaseFragment() {
    private lateinit var viewModel: PeopleListViewModel
    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PeopleListViewModelFactory(PeopleRepository(SWServiceClient.getService()))
        )[PeopleListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPeople()
        observeApiLoadingEvent(viewModel)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.people.observe(viewLifecycleOwner) {

        }
    }

    private fun getPeople() {
        val peopleObserver = object : SWApiLiveDataObserver<Results<People>>() {
            override fun onSuccess(data: Results<People>?) {
                val people = data!!
                binding.progressBar.visibility = View.GONE
                binding.peopleRecycler.visibility = View.VISIBLE
                binding.peoplePreviousButton.isEnabled = people.previous != null
                binding.peopleNextButton.isEnabled = people.next != null
                val adapter = PeopleAdapter(people.results)
                binding.peopleRecycler.adapter = adapter
                binding.peopleRecycler.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = errorMessage
                //　TODO 再試行ボタン追加
            }

            override fun onLoading() {
                super.onLoading()
                binding.peopleRecycler.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }

        viewModel.getPeople().observe(viewLifecycleOwner, peopleObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}