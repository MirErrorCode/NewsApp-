package com.mirlink.news.domain.usecase

import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor (
    private val repository: NewsRepository
){
    operator fun invoke(): Flow<List<News>> {
        return repository.getFavorites()
    }
}