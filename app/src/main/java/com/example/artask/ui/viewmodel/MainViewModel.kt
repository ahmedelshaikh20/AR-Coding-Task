package com.example.artask.ui.viewmodel

import ArticleModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
      if (state.query!="")
      state = state.copy(articles = getAllArticles(state.query))
      else {
        state = state.copy(articles = emptyList())

      }
    }
  }

  fun updateFavArticle(article: ArticleModel) {
    viewModelScope.launch {
      addFavouriteArticle(article.copy(isFav = !article.isFav))
      updateArticles()

    }

  }

  fun getAllFav() {
    viewModelScope.launch {
      state = state.copy(favArticles = getAllFavoriteArticles(state.query))
    }
  }

  private fun updateArticles() {
    viewModelScope.launch {
      state = state.copy(articles = getAllArticles(state.query))
      state = state.copy(favArticles = getAllFavoriteArticles(state.query))


    }
  }
   fun clearArticles() {
    viewModelScope.launch {
      state = state.copy(articles = emptyList())
      state = state.copy(favArticles = emptyList())
    }
  }

  fun updateQuery(query: String) {
    viewModelScope.launch {
      state = state.copy(query = query)
    }
  }

  fun updateVisibility(target:String) {
    viewModelScope.launch {
      state = state.copy(showFavOnly = target != "All")
    }
  }



}
