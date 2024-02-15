package com.darthkiler.ookgrouptestproject.domain.internal

import androidx.paging.PagingData
import androidx.paging.map
import com.darthkiler.ookgrouptestproject.domain.model.PostcardModel
import com.darthkiler.ookgrouptestproject.domain.model.toPostcardModel
import com.darthkiler.ookgrouptestproject.network.api.postcards.PostcardsNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PostcardsDomainService {
    fun getPostcardsList(): Flow<PagingData<PostcardModel>>
}

internal class PostcardsDomainServiceImpl @Inject constructor(
    private val postcardsNetworkRepository: PostcardsNetworkRepository
) : PostcardsDomainService {
    override fun getPostcardsList(): Flow<PagingData<PostcardModel>> {
        return postcardsNetworkRepository.getPostcards().map {
            it.map {
                it.toPostcardModel()
            }
        }
    }

}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PostcardsDomainServiceModule {

    @Binds
    internal abstract fun provideModule(
        postcardsDomainServiceImpl: PostcardsDomainServiceImpl
    ): PostcardsDomainService
}