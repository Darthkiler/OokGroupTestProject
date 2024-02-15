package com.darthkiler.ookgrouptestproject.domain.model

import com.darthkiler.ookgrouptestproject.network.api.postcards.model.PostcardsResponseBody

data class PostcardModel(
    val imageUrl: String?
)

fun PostcardsResponseBody.Data.Postcard.toPostcardModel() = PostcardModel(
    "https://cdn.otkritkiok.ru/posts/thumbs/" + this.image?.replace(
        "gif",
        "jpg"
    )
)