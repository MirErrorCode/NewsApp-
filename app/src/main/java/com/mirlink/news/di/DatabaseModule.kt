package com.mirlink.news.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mirlink.news.data.local.dao.NewsDao
import com.mirlink.news.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {
        return Room.databaseBuilder(
            context,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

    @Provides
    fun provideNewsDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }

}