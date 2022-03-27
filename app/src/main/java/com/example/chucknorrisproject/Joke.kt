package com.example.chucknorrisproject

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class Joke(
    val categories: List<Any>,
    val createdAt: String,
    val iconUrl: String,
    val id: String,
    val updatedAt: String,
    val url: String,
    val value: String
)