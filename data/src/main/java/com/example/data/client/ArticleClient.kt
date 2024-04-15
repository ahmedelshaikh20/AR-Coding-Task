package com.example.data.client

import ArticleModel
import android.util.Log
import com.example.data.api.ArticleApi
import com.example.data.database.ArticleDatabase
import com.example.data.database.mappers.toArticleEntity
import com.example.data.database.mappers.toArticleModel
import com.example.data.database.model.ArticleEntity
import javax.inject.Inject

class ArticleClient @Inject constructor(
  private val articleApi: ArticleApi,
  private val database: ArticleDatabase
) {

  suspend fun getAllArticles(query: String): List<ArticleModel> {
    // check if db contain the file
    val dao = database.dao
    var response = dao.getAllArticles(query)
    var result = response.map {

      it.toArticleModel()
    }

   // Log.d("YEES" ,result.isEmpty().toString() )
    if (result.isEmpty()) {
      // fetch from remote
      val articles = articleApi.getAllArticles(query).articles
      if (articles.isNotEmpty()){

      val res = articles.map  {
      it.toArticleEntity(query ,false)
    }
      dao.addArticle(res)}
    }else
    {
      return result
    }
     response = dao.getAllArticles(query)
     result = response.map {
      it.toArticleModel()
    }
    return result
  }

  suspend fun getAllFavoriteArticles(query: String): List<ArticleModel> {
return database.dao.getFavArticles(query).map {
  it.toArticleModel()
}
  }

  suspend fun addFavArticle(articleEntity: ArticleEntity) {
database.dao.updateFavArticles(articleEntity)
  }

}
