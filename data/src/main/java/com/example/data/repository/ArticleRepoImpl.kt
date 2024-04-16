package com.example.data.repository


import ArticleModel
import com.example.data.client.ArticleClient
import com.example.data.database.mappers.toArticleEntity
import com.example.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepoImpl @Inject constructor(private val articleClient: ArticleClient) :
  ArticleRepository {
  override suspend fun getAllArticles(query: String): List<ArticleModel> {
    val response = articleClient.getAllArticles(query)
    return response
  }

  override suspend fun getAllFavoriteArticles(query: String): List<ArticleModel> {
    return articleClient.getAllFavoriteArticles(query)
  }

  override suspend fun addFavArticle(articleModel: ArticleModel) {
    val articleEntity = articleModel.toArticleEntity()
    articleClient.addFavArticle(articleEntity)
  }
}
