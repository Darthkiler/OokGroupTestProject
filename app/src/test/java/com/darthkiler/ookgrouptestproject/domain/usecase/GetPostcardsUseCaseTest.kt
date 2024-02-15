package com.darthkiler.ookgrouptestproject.domain.usecase

import androidx.paging.PagingData
import com.darthkiler.ookgrouptestproject.domain.internal.PostcardsDomainService
import com.darthkiler.ookgrouptestproject.domain.model.PostcardModel
import com.google.common.truth.Truth.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPostcardsUseCaseTest {

    private val postcardsDomainService = mockk<PostcardsDomainService>()

    private val getPostcardsUseCase = GetPostcardsImpl(
        postcardsDomainService
    )

    /**
     * При вызове юзкейса вызывает метод в сервисе и возвращает его результат
     */

    @Test
    fun test() {

        // GIVEN
        val expectedResult = mockk<Flow<PagingData<PostcardModel>>>()
        every { postcardsDomainService.getPostcardsList() } returns expectedResult

        // WHEN
        val result = getPostcardsUseCase()

        // THEN
        assertThat(result).isEqualTo(expectedResult)
        verify { postcardsDomainService.getPostcardsList() }
    }
}
