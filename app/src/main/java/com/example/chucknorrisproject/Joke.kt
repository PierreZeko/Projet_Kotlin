package com.example.chucknorrisproject

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Joke(

    val categories: List<String> = emptyList(),
    @SerialName("created_at") val createdAt: String = "2020-01-05 13:42:29.296379",
    @SerialName("icon_url") val iconUrl: String = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    val id: String = "p3GHoau2SLGTv04XhtIeeg",
    @SerialName("updated_at") val updatedAt: String = "2020-01-05 13:42:29.296379",
    val url: String = "https://api.chucknorris.io/jokes/p3GHoau2SLGTv04XhtIeeg",
    val value: String,
    val saveJoke: Boolean = false,
    val sharedJoke: Boolean = false
    )