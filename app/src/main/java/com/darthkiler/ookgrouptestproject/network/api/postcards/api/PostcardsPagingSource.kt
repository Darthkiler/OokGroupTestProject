package com.darthkiler.ookgrouptestproject.network.api.postcards.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.darthkiler.ookgrouptestproject.network.api.postcards.model.PostcardsResponseBody
import com.darthkiler.ookgrouptestproject.network.utils.RequestConstants
import com.darthkiler.ookgrouptestproject.network.utils.RequestConstants.PAGE
import javax.inject.Inject

class PostcardsPagingSource @Inject constructor(
    private val postcardsApi: PostcardsApi
): PagingSource<Int, PostcardsResponseBody.Data.Postcard>() {
    override fun getRefreshKey(state: PagingState<Int, PostcardsResponseBody.Data.Postcard>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostcardsResponseBody.Data.Postcard> {
        return try {
            val currentPage = params.key ?: 1
            val results = postcardsApi.getPostcards(
                headers = mapOf(
                    RequestConstants.AUTH to "ookgroup"
                ),
                queryParams = mapOf(
                    RequestConstants.LIMIT to RequestConstants.ITEMS_PER_PAGE.toString(),
                    PAGE to currentPage.toString(),
                )
            )
            LoadResult.Page(
                data = results.data.postcards ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = results.data.totalPages?.let { totalPages ->
                    currentPage.plus(1).takeIf {
                        totalPages >= it
                    }
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}