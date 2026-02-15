package com.mirlink.news.presentation.screens.details

import com.mirlink.news.domain.model.News

data class DetailsUiState(
    val isLoading: Boolean = false,
    val news: News? = null,
    val error: String? = null
)