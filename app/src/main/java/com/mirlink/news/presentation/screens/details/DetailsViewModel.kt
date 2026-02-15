package com.mirlink.news.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirlink.news.domain.usecase.GetNewsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getNewsById: GetNewsByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsUiState())
    val uiState: StateFlow<DetailsUiState> = _uiState

    fun loadNews(id: String) {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            try {
                val news = getNewsById(id)
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
}
