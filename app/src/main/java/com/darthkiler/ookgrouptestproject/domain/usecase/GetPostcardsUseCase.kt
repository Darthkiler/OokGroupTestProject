package com.darthkiler.ookgrouptestproject.domain.usecase

import androidx.paging.PagingData
import com.darthkiler.ookgrouptestproject.domain.internal.PostcardsDomainService
import com.darthkiler.ookgrouptestproject.domain.model.PostcardModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetPostcardsUseCase {
    operator fun invoke(): Flow<PagingData<PostcardModel>>
}

internal class GetPostcardsImpl @Inject constructor(
    private val postcardsDomainService: PostcardsDomainService
) : GetPostcardsUseCase {
    override fun invoke(): Flow<PagingData<PostcardModel>> {
        return postcardsDomainService.getPostcardsList()
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class GetPostcardsModule {

    @Binds
    internal abstract fun provideModule(
        getPostcardsImpl: GetPostcardsImpl
    ): GetPostcardsUseCase
}
