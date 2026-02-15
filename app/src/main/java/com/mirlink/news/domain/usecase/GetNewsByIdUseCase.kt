package com.mirlink.news.domain.usecase

import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.repository.NewsRepository
import jakarta.inject.Inject

class GetNewsByIdUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: String): News? {
        return repository.getTopNews().find { it.id == id }
    }
}