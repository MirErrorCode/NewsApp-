package com.mirlink.news.domain.model

data class News(
    val id: String,
    val title: String,
    val description: String,
    val content: String,
    val imageUrl: String?,
    val isFavorite: Boolean
)