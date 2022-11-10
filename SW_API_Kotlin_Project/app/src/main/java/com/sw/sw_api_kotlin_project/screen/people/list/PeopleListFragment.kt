package com.sw.sw_api_kotlin_project.screen.people.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.databinding.FragmentPeopleListBinding
import com.sw.sw_api_kotlin_project.model.entity.PageType
import com.sw.sw_api_kotlin_project.screen.base.BaseFragment
import com.sw.sw_api_kotlin_project.screen.people.PeopleAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * 登場人物一覧画面
 */
@AndroidEntryPoint
class PeopleListFragment : BaseFragment() {
    private val viewModel: PeopleListViewModel by viewModels()
    private var _binding: FragmentPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        super.initView()
        binding.apply {
            peopleListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
                setOnClickListener {
                    activity?.finish()
                }
                title = getString(R.string.people_list_title)
            }
            nextButton.setOnClickListener {
                viewModel.getPeople(PageType.NEXT_PAGE)
            }
            previousButton.setOnClickListener {
                viewModel.getPeople(PageType.PREVIOUS_PAGE)
            }
            retryButton.setOnClickListener {
                viewModel.getPeople(PageType.CURRENT_PAGE)
            }
        }
        viewModel.getPeople(PageType.FIRST_PAGE)
    }

    override fun addObservers() {
        super.addObservers()
        viewModel.peopleList.observe(viewLifecycleOwner) { people ->
            binding.peopleRecyclerView.isVisible = true
            binding.previousButton.isEnabled = people.previous != null
            binding.nextButton.isEnabled = people.next != null
            binding.peopleRecyclerView.adapter = PeopleAdapter(
                peopleList = people.results
            ) {
                val action = PeopleListFragmentDirections.actionNavPeopleListToNavPeopleDetail(it)
                findNavController().navigate(action)
            }
        }
        viewModel.failureMessage.observe(viewLifecycleOwner) { failureMessage ->
            binding.peopleRecyclerView.adapter = null
            binding.peopleRecyclerView.isVisible = false
            binding.errorText.isVisible = true
            binding.errorText.text = failureMessage
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.errorText.isVisible = isLoading
            binding.peopleRecyclerView.isVisible = !isLoading
            binding.previousButton.isVisible = !isLoading
            binding.nextButton.isVisible = !isLoading
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}