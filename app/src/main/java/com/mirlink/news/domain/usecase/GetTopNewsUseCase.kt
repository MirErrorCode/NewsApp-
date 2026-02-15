package com.mirlink.news.domain.usecase

import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetTopNewsUseCase @Inject constructor(
    private val repository: NewsRepository
){
    suspend operator fun invoke() : List<News>{
        return repository.getTopNews()
    }
}