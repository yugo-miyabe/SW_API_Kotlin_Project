package com.sw.sw_api_kotlin_project.model.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sw.sw_api_kotlin_project.network.SWService
import com.sw.sw_api_kotlin_project.network.model.People
import com.sw.sw_api_kotlin_project.network.model.Results
import okio.IOException
import javax.inject.Inject

private const val FIRST_PAGE_KEY = 1

class PeoplePagingSource @Inject constructor(private val swService: SWService) :
    PagingSource<Int, People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        val position: Int = params.key ?: FIRST_PAGE_KEY
        return try {
            val response: Results<People> = swService.getPeople(position)
            val nextKey = if (response.next == null) null else position + 1
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == FIRST_PAGE_KEY) null else position - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}