package com.darthkiler.ookgrouptestproject.network.api.postcards.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostcardsResponseBody(
    @SerialName("status")
    val status: String?,
    @SerialName("data")
    val data: Data,


) {
    @Serializable
    data class Data(
        @SerialName("postcards")
        val postcards: List<Postcard>?,

        @SerialName("totalPostcards")
        val totalPostcards: Int?,
        @SerialName("totalPages")
        val totalPages: Int?,
    ) {
        @Serializable
        data class Postcard(
            @SerialName("image")
            val image: String?
        )
    }
}