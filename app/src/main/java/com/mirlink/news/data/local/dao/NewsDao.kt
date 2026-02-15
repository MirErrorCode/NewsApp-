package com.mirlink.news.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mirlink.news.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllFavorites(): Flow<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: NewsEntity)

    @Delete
    suspend fun delete(news: NewsEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM news WHERE id = :id)")
    suspend fun isFavorite(id: String): Boolean

}