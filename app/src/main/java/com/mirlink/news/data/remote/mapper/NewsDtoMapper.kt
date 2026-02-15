package com.mirlink.news.data.remote.mapper

import com.mirlink.news.data.remote.dto.NewsDto
import com.mirlink.news.domain.model.News

fun NewsDto.toDomain(): News {
    return News(
        id = uuid,
        title = title,
        description = description.orEmpty(),
        content = snippet.orEmpty(),
        imageUrl = image_url,
        isFavorite = false
    )
}