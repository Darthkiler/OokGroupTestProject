package com.darthkiler.ookgrouptestproject.network.api.postcards.api

import com.darthkiler.ookgrouptestproject.network.api.postcards.model.PostcardsResponseBody
import com.darthkiler.ookgrouptestproject.network.utils.RequestConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import javax.inject.Singleton

interface PostcardsApi {

    @GET(RequestConstants.POSTCARDS_LIST_PATH)
    suspend fun getPostcards(
        @HeaderMap headers: Map<String, String>,
        @QueryMap queryParams: Map<String, String>
    ): PostcardsResponseBody
}

@Module
@InstallIn(SingletonComponent::class)
internal class PostcardsApiModule {

    @Singleton
    @Provides
    internal fun provideModule(
        retrofit: Retrofit.Builder
    ) = retrofit.build().create(PostcardsApi::class.java)
}
