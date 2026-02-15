package com.mirlink.news.data.repository

import android.content.Context
import com.mirlink.news.data.local.dao.NewsDao
import com.mirlink.news.data.remote.api.NewsApi
import com.mirlink.news.data.remote.mapper.toDomain
import com.mirlink.news.data.remote.mapper.toEntity
import com.mirlink.news.domain.model.News
import com.mirlink.news.domain.repository.NewsRepository
import com.mirlink.news.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao,
    @ApplicationContext private val context: Context

) : NewsRepository {

    private val apiKey = Constants.API_KEY

    override suspend fun getTopNews(): List<News> {
        val response = api.getTopNews(apiKey)

        return response.data.map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun searchNews(query: String): List<News> {
        val response = api.searchNews(apiKey, query)

        return response.data.map { dto ->
            dto.toDomain()
        }
    }

    override fun getFavorites(): Flow<List<News>> {
        return dao.getAllFavorites().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun toggleFavorite(news: News) {
        if (dao.isFavorite(news.id)) {
            dao.delete(news.toEntity())
        } else {
            dao.insert(news.toEntity())
        }
    }

}