package com.mirlink.news.presentation.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.usecase.GetFavoritesUseCase
import com.mirlink.news.domain.usecase.GetTopNewsUseCase
import com.mirlink.news.domain.usecase.SearchNewsUseCase
import com.mirlink.news.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getTopNews: GetTopNewsUseCase,
    private val searchNews: SearchNewsUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
    private val getFavorites: GetFavoritesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState

    private val favoritesFlow = getFavorites()

    private val newsFlow = MutableStateFlow<List<News>>(emptyList())

    init {
        observeNews()
        refreshNews()
    }

    fun loadTopNews() {
        viewModelScope.launch {

            val newsList = getTopNews()

            favoritesFlow.collect { favorites ->

                val updated = newsList.map { article ->
                    article.copy(
                        isFavorite = favorites.any { it.id == article.id }
                    )
                }

                _uiState.update {
                    it.copy(
                        news = updated,
                        isLoading = false
                    )
                }
            }
        }
    }


    fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(
                searchQuery = query
            )
        }

        if (query.isBlank()) {
            loadTopNews()
        } else {
            search(query)
        }
    }


    private fun search(query: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {
                val news = searchNews(query)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        news = news
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }

    fun onToggleFavorite(news: News) {
        viewModelScope.launch {
            toggleFavorite(news)
        }
    }
    fun refreshNews() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                val news = getTopNews()
                newsFlow.value = news
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message)
                }
            }
        }
    }

    private fun observeNews() {
        viewModelScope.launch {
            combine(
                newsFlow,
                getFavorites()
            ) { news, favorites ->

                news.map { article ->
                    article.copy(
                        isFavorite = favorites.any { it.id == article.id }
                    )
                }

            }.collect { updatedNews ->

                _uiState.update {
                    it.copy(news = updatedNews)
                }
            }
        }
    }


}