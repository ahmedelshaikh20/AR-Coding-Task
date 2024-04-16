package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleEntity(
  @PrimaryKey
  val id: Int? = null,
  val title: String,
  val description: String? = "Empty Description",
  val author: String? = "UnKnown Author",
  val query: String,
  val isFav: Boolean,
  val publishDate: String
)
