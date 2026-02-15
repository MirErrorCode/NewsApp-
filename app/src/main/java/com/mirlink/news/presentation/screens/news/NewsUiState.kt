package com.mirlink.news.presentation.screens.news

import com.mirlink.news.domain.model.News


data class NewsUiState (
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)