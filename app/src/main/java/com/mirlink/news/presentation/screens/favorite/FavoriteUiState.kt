package com.mirlink.news.presentation.screens.favorite

import com.mirlink.news.domain.model.News

data class FavoriteUiState(
    val isLoading: Boolean = false,
    val news: List<News> = emptyList(),
    val error: String? = null,
    val searchQuery: String = ""
)