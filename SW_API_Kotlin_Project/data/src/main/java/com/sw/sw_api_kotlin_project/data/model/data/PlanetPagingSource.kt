package com.sw.sw_api_kotlin_project.data.model.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sw.sw_api_kotlin_project.data.network.model.SWService
import com.sw.sw_api_kotlin_project.data.network.model.Planet
import com.sw.sw_api_kotlin_project.data.network.model.Results
import okio.IOException

class PlanetPagingSource(private val swService: SWService) : PagingSource<Int, Planet>() {
    private val firstPageKey = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        val position: Int = params.key ?: firstPageKey
        return try {
            val response: Results<Planet> = swService.getPlanets(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (response.previous == null) null else position - 1,
                nextKey = if (response.next == null) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
