package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.dao.ArticleDao
import com.example.data.database.model.ArticleEntity

@Database(entities =  [ArticleEntity::class] , version = 2)
abstract class ArticleDatabase : RoomDatabase() {
  abstract val dao : ArticleDao
}
