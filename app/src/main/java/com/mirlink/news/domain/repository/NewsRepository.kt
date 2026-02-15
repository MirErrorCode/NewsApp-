package com.mirlink.news.domain.repository

import androidx.room.Query
import com.mirlink.news.domain.model.News
import kotlinx.coroutines.flow.Flow


interface NewsRepository {

    suspend fun getTopNews() : List<News>

    suspend fun searchNews(query: String): List<News>

    fun getFavorites(): Flow<List<News>>

    suspend fun toggleFavorite(news: News)


}