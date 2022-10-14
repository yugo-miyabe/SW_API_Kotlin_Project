package com.sw.sw_api_kotlin_project.activity.people_list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.sw.sw_api_kotlin_project.R
import com.sw.sw_api_kotlin_project.adapter.PeopleAdapter
import com.sw.sw_api_kotlin_project.api.liveData.SWLiveDataObserver
import com.sw.sw_api_kotlin_project.base.BaseActivity
import com.sw.sw_api_kotlin_project.data.model.People
import com.sw.sw_api_kotlin_project.data.model.Results
import com.sw.sw_api_kotlin_project.databinding.ActivityPeopleListBinding
import com.sw.sw_api_kotlin_project.utils.PageType
import dagger.hilt.android.AndroidEntryPoint

/**
 * 登場人物一覧画面
 */
@AndroidEntryPoint
class PeopleListActivity : BaseActivity() {
    private var _binding: ActivityPeopleListBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val viewModel: PeopleListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPeopleListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            peopleListAppbar.findViewById<MaterialToolbar>(R.id.toolbar).apply {
                setOnClickListener { view ->
                    finish()
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
                    // TODO 画面遷移
                    /*
                    val action = PeopleListFragmentDirections.actionNavPeopleToNavPeopleDetail(it)
                    findNavController().navigate(action)
                    */
                }
                binding.peopleRecyclerView.adapter = adapter
                binding.peopleRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
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
        viewModel.getPeople(pageType).observe(this, peopleObserver)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}