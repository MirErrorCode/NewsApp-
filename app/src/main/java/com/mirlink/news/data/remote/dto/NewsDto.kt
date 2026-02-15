package com.mirlink.news.data.remote.dto

data class NewsDto(
    val uuid: String,
    val title: String,
    val description: String?,
    val snippet: String?,
    val image_url: String?,
    val source: String?,
    val published_at: String?
)
