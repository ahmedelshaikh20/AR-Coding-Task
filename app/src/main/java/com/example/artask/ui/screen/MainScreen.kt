package com.example.artask.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.artask.R
import com.example.artask.ui.viewmodel.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
  val state = viewModel.state
  Column() {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp),
      horizontalArrangement = Arrangement.SpaceAround,

      ) {
      ButtonComponent(value = "All", isEnabled = false, onClick = {
        viewModel.updateVisibility()
        viewModel.getArticles()
      })
      ButtonComponent(
        value = "Favourite",
        isEnabled = true,
        onClick = {
          viewModel.updateVisibility()
          viewModel.getAllFav()
        })
    }
    SearchBar(onQueryChange = { viewModel.updateQuery(it) },
      onSearchClick = { viewModel.getArticles() })
    AnimatedVisibility(visible =state.showFavOnly ) {


    LazyColumn(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      items(state.articles) { article ->
        ResultItem(
          modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .background(
              colorResource(id = R.color.background)
            ),
          author = article.author.toString(),
          title = article.title,
          onClick = {},
          onFavoriteClick = {
            viewModel.updateFavArticle(article)
          },
          isFav = article.isFav)
      }
    }

  }
    AnimatedVisibility(visible =!state.showFavOnly ) {


      LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        items(state.favArticles) { article ->
          ResultItem(
            modifier = Modifier
              .padding(vertical = 10.dp, horizontal = 10.dp)
              .background(
                colorResource(id = R.color.background)
              ),
            author = article.author.toString(),
            title = article.title,
            onClick = {},
            onFavoriteClick = {
              viewModel.updateFavArticle(article)
            },
            isFav = article.isFav)
        }
      }

    }}
}
