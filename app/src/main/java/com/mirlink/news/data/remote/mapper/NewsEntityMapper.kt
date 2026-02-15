package com.mirlink.news.data.remote.mapper

import com.mirlink.news.data.local.entity.NewsEntity
import com.mirlink.news.domain.model.News


fun NewsEntity.toDomain(): News {
    return News(
        id = id,
        title = title,
        description = description,
        content = content,
        imageUrl = imageUrl,
        isFavorite = true,
    )
}

fun News.toEntity(): NewsEntity {
    return NewsEntity(
        id = id,
        title = title,
        description = description,
        content = content,
        imageUrl = imageUrl
    )
}