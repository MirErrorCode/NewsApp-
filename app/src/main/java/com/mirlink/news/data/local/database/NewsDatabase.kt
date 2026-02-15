package com.mirlink.news.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirlink.news.data.local.dao.NewsDao
import com.mirlink.news.data.local.entity.NewsEntity


@Database(
    entities = [NewsEntity::class],
    version = 1
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}