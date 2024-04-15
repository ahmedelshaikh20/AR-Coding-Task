package com.example.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.database.model.ArticleEntity

@Dao
interface ArticleDao {

  @Insert
  suspend fun addArticle(articles: List<ArticleEntity>)

  @Query("Select * from ArticleEntity Where `query` LIKE :query  ")
  suspend fun getAllArticles(query: String): List<ArticleEntity>

  @Query("Select * from ArticleEntity Where isFav = 1 And  `query` LIKE :query ")
  suspend fun getFavArticles(query: String): List<ArticleEntity>

  @Update
  suspend fun updateFavArticles(article: ArticleEntity)

}
