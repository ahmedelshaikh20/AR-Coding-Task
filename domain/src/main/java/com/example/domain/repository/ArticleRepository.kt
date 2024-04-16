package com.example.domain.repository

import ArticleModel

interface ArticleRepository {
  suspend fun getAllArticles(query:String):List<ArticleModel>
  suspend fun getAllFavoriteArticles(query: String): List<ArticleModel>
  suspend fun addFavArticle(articleModel: ArticleModel)
}
