package com.mirlink.news.domain.usecase

import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
){
    suspend operator fun invoke(query: String): List<News>{
        return repository.searchNews(query)
    }
}