package com.sw.sw_api_kotlin_project.screen.film.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sw.sw_api_kotlin_project.data.model.repository.FilmRepository
import com.sw.sw_api_kotlin_project.data.network.model.Film
import com.sw.sw_api_kotlin_project.screen.base.BaseViewModelTest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FilmListViewModel @Inject constructor(
    private val filmRepository: FilmRepository
) : BaseViewModelTest() {

    val filmItems: Flow<PagingData<Film>> = Pager(config = PagingConfig(
        pageSize = 1,
        enablePlaceholders = false,
    ), pagingSourceFactory = {
        filmRepository.filmListPagingSource()
    }).flow.cachedIn(viewModelScope)
}
