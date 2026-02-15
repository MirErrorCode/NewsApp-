package com.mirlink.news.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.usecase.GetFavoritesUseCase
import com.mirlink.news.domain.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavorites: GetFavoritesUseCase,
    private val toggleFavorite: ToggleFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            getFavorites().collect { favorites ->
                _uiState.update {
                    it.copy(news = favorites)
                }
            }
        }
    }


    fun onToggleFavorite(news: News) {
        viewModelScope.launch {

            toggleFavorite(news)

            _uiState.update { currentState ->

                val updatedList = currentState.news.map {
                    if (it.id == news.id) {
                        it.copy(isFavorite = !it.isFavorite)
                    } else it
                }

                currentState.copy(news = updatedList)
            }
        }
    }

}