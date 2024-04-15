package com.example.artask.ui.viewmodel

import ArticleModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.data.model.ArticlesResponse
import com.example.domain.usecases.AddFavouriteArticle
import com.example.domain.usecases.GetAllArticles
import com.example.domain.usecases.GetAllFavoriteArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  val getAllArticles: GetAllArticles,
  val addFavouriteArticle: AddFavouriteArticle,
  val getAllFavoriteArticles: GetAllFavoriteArticles
) : ViewModel() {

  var state by mutableStateOf(MainScreenState(emptyList(), emptyList(), "", false))

  fun getArticles() {
    viewModelScope.launch {
      state = state.copy(articles = getAllArticles(state.query))
    }
  }

  fun updateFavArticle(article: ArticleModel) {
    viewModelScope.launch {
      addFavouriteArticle(article.copy(isFav = !article.isFav))
      updateArticles()
      Log.d("YESS", state.favArticles.size.toString())
    }

  }

  fun getAllFav() {
    viewModelScope.launch {
      state = state.copy(favArticles = getAllFavoriteArticles(state.query))
    }
  }

  fun updateArticles() {
    viewModelScope.launch {
      state = state.copy(articles = getAllArticles(state.query))
    }
  }

  fun updateQuery(query: String) {
    viewModelScope.launch {
      state = state.copy(query = query)
    }
  }

  fun updateVisibility() {
    viewModelScope.launch {
      state = state.copy(showFavOnly = !state.showFavOnly)
    }
  }

  init {
  }

}
