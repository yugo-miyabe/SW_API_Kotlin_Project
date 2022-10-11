package com.sw.sw_api_kotlin_project.fragment.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.PeopleAdapter
import com.sw.sw_api_kotlin_project.api.SWServiceClient
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseFragment
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.utils.PageType

/**
 * 登場人物一覧画面
 */
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
        binding.apply {
            peopleListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
                setOnClickListener { view ->
                    view.findNavController().navigateUp()
                }
                title = getString(R.string.people_list_title)
            }
            nextButton.setOnClickListener {
                getPeople(PageType.NEXT_PAGE)
            }
            previousButton.setOnClickListener {
                getPeople(PageType.PREVIOUS_PAGE)
            }
            retryButton.setOnClickListener {
                getPeople(PageType.CURRENT_PAGE)
            }
        }
        getPeople(PageType.FIRST_PAGE)
    }

    private fun getPeople(pageType: PageType) {
        val peopleObserver = object : SWLiveDataObserver<Results<People>>() {
            override fun onSuccess(data: Results<People>?) {
                val people = data!!
                binding.previousButton.isEnabled = people.previous != null
                binding.nextButton.isEnabled = people.next != null
                val adapter = PeopleAdapter(
                    people.results,
                ) {
                    val action = PeopleListFragmentDirections.actionNavPeopleToNavPeopleDetail(it)
                    findNavController().navigate(action)
                }
                binding.peopleRecyclerView.adapter = adapter
                binding.peopleRecyclerView.layoutManager = LinearLayoutManager(context)
            }

            override fun onError(errorMessage: String) {
                binding.progressBar.isVisible = false
                binding.retryButton.isVisible = true
                binding.errorText.isVisible = true
                binding.errorText.text = errorMessage
            }

            override fun onLoading() {
                super.onLoading()
                binding.retryButton.isVisible = false
                binding.errorText.isVisible = false
            }

            override fun onViewChange(shouldListShow: Boolean) {
                super.onViewChange(shouldListShow)
                binding.progressBar.isVisible = !shouldListShow
                binding.peopleRecyclerView.isVisible = shouldListShow
                binding.previousButton.isVisible = shouldListShow
                binding.nextButton.isVisible = shouldListShow
            }
        }
        viewModel.getPeople(pageType).observe(viewLifecycleOwner, peopleObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}