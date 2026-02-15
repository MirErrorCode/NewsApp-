package com.mirlink.news.data.remote.api

import com.mirlink.news.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {

    @GET("news/top")
    suspend fun getTopNews(
        @Query("api_token") apiKey: String,
        @Query("locale") locale: String = "us",
        @Query("limit") limit: Int = 20
    ): NewsResponseDto

    @GET("news/all")
    suspend fun searchNews(
        @Query("api_token") apiKey: String,
        @Query("search") query: String,
        @Query("locale") locale: String = "us",
        @Query("limit") limit: Int = 20
    ): NewsResponseDto

}