package com.example.artask.ui.viewmodel

import ArticleModel

data class MainScreenState (
  val articles : List<ArticleModel> ,
  val favArticles  : List<ArticleModel>,
  val query : String,
  val showFavOnly : Boolean
  )
