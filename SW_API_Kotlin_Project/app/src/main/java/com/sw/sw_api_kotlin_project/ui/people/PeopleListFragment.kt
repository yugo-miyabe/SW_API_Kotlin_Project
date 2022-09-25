package com.sw.sw_api_kotlin_project.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sw.sw_api_kotlin_project.adapters.PeopleAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWApiLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.database.FavoriteDatabase
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.repository.FavoriteRepository
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.utils.PageType

class PeopleListFragment : BaseFragment() {
    private lateinit var viewModel: PeopleListViewModel
    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            PeopleListViewModelFactory(
                PeopleRepository(
                    SWServiceClient.getService(),
                ),
                FavoriteRepository(
                    FavoriteDatabase.getDatabase(activity?.application!!).FavoriteDao(),
                ),
            )
        )[PeopleListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.nextButton.setOnClickListener {
            getPeople(PageType.NEXT_PAGE)
        }
        binding.previousButton.setOnClickListener {
            getPeople(PageType.PREVIOUS_PAGE)
        }
        binding.retryButton.setOnClickListener {
            getPeople(PageType.CURRENT_PAGE)
        }
        getPeople(PageType.FIRST_PAGE)
    }

    private fun getPeople(pageType: PageType) {
        val peopleObserver = object : SWApiLiveDataObserver<Results<People>>() {
            override fun onSuccess(data: Results<People>?) {
                val people = data!!
                binding.progressBar.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
                binding.previousButton.visibility = View.VISIBLE
                binding.nextButton.visibility = View.VISIBLE
                binding.retryButton.visibility = View.GONE
                binding.previousButton.isEnabled = people.previous != null
                binding.nextButton.isEnabled = people.next != null
                val adapter = PeopleAdapter(people.results) {
                    val action = PeopleListFragmentDirections.actionNavPeopleToNavPeopleDetail(it)
                    findNavController().navigate(action)
                }
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
                binding.retryButton.visibility = View.VISIBLE
                binding.errorText.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.recyclerView.visibility = View.GONE
                binding.previousButton.visibility = View.GONE
                binding.nextButton.visibility = View.GONE
                binding.retryButton.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }
        }
        viewModel.getPeople(pageType).observe(viewLifecycleOwner, peopleObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}