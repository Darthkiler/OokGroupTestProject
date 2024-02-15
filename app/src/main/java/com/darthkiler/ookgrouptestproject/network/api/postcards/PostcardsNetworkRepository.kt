package com.darthkiler.ookgrouptestproject.network.api.postcards

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.darthkiler.ookgrouptestproject.network.api.postcards.api.PostcardsPagingSource
import com.darthkiler.ookgrouptestproject.network.api.postcards.model.PostcardsResponseBody
import com.darthkiler.ookgrouptestproject.network.utils.RequestConstants
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface PostcardsNetworkRepository {
    fun getPostcards(): Flow<PagingData<PostcardsResponseBody.Data.Postcard>>
}

internal class PostcardsNetworkRepositoryImpl @Inject constructor(
    private val postcardsPagingSource: PostcardsPagingSource
): PostcardsNetworkRepository {
    override fun getPostcards(): Flow<PagingData<PostcardsResponseBody.Data.Postcard>> {
        return Pager(
            config = PagingConfig(
                pageSize = RequestConstants.ITEMS_PER_PAGE
            ),
            pagingSourceFactory = {
                postcardsPagingSource
            }
        ).flow
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class PostcardsNetworkRepositoryModule {

    @Binds
    internal abstract fun provideModule(
        postcardsNetworkRepository: PostcardsNetworkRepositoryImpl
    ): PostcardsNetworkRepository
}