package com.example.data.database.mappers

import ArticleModel
import com.example.data.database.model.ArticleEntity
import com.example.data.model.Article

fun Article.toArticleEntity(query: String, isFav: Boolean): ArticleEntity {
  return ArticleEntity(
    title = title,
    author = author,
    description = description,
    query = query,
    isFav = isFav,
    publishDate = publishedAt
  )
}


fun ArticleEntity.toArticleModel(): ArticleModel {
  return ArticleModel(
    id = id,
    title = title,
    author = author,
    description = description,
    query = query,
    isFav = isFav,
    publishDate = publishDate

  )
}


fun ArticleModel.toArticleEntity(): ArticleEntity {
  return ArticleEntity(
    id = id,
    title = title,
    author = author,
    description = description,
    query = query,
    isFav = isFav,
    publishDate = publishDate

  )
}
