package com.darthkiler.ookgrouptestproject.domain.model

import com.darthkiler.ookgrouptestproject.network.api.postcards.model.PostcardsResponseBody
import com.google.common.truth.Truth.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class PostcardModelTest {

    /**
     * Правращает одну модель в другую
     */
    @Test
    fun test() {

        // GIVEN
        val givenNetworkModel = PostcardsResponseBody.Data.Postcard(
            image = "randomImagePath.gif"
        )
        val expectedResult = PostcardModel(
            imageUrl = "https://cdn.otkritkiok.ru/posts/thumbs/randomImagePath.jpg"
        )

        // WHEN
        val result = givenNetworkModel.toPostcardModel()

        // THEN
        assertThat(result).isEqualTo(expectedResult)
    }
}
