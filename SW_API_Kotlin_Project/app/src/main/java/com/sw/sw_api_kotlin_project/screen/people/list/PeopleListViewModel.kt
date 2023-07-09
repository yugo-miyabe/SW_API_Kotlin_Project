package com.sw.sw_api_kotlin_project.screen.people.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sw.sw_api_kotlin_project.data.model.repository.PeopleRepository
import com.sw.sw_api_kotlin_project.data.network.model.People
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModel
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModelTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(
    private val peopleRepository: PeopleRepository,
) : BaseViewModelTest() {

    val peopleItems: Flow<PagingData<People>> = Pager(config = PagingConfig(
        pageSize = 1,
        enablePlaceholders = false,
    ), pagingSourceFactory = {
        peopleRepository.peopleListPagingSource()
    }).flow.cachedIn(viewModelScope)


}
