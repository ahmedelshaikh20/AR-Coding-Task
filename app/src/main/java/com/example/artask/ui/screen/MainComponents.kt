package com.example.artask.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artask.R
import com.example.artask.ui.theme.fontsfamilys


@Composable
fun BasicTextField(value: String, modifier: Modifier = Modifier) {
  Text(
    text = value,
    modifier = modifier
      .fillMaxWidth()
      .heightIn(min = 70.dp),
    fontFamily = fontsfamilys.poppinsFamily,
    fontStyle = FontStyle.Normal,
    fontWeight = FontWeight(200),
    color = colorResource(id = R.color.black),
    textAlign = TextAlign.Center
  )
}

@Composable
fun ButtonComponent(
  value: String,
  onClick: () -> (Unit),
  isEnabled: Boolean = false,
  modifier: Modifier = Modifier
) {
  Button(modifier = modifier
    .widthIn()
    .heightIn(min = 48.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = if (isEnabled) colorResource(id = R.color.SearchContainerColor) else colorResource(
        id = R.color.black
      )
    ),
    shape = RoundedCornerShape(5.dp),
    onClick = {
      onClick()
    }) {
    BoldTextField(
      value = value,
      color = if (isEnabled) colorResource(id = R.color.black) else colorResource(id = R.color.SearchContainerColor),
      size = 22.sp
    )

  }
}

@Composable
fun BoldTextField(value: String, size: TextUnit, color: Color, modifier: Modifier = Modifier) {
  Text(
    text = value,
    modifier = modifier
      .heightIn(),
    fontFamily = fontsfamilys.poppinsFamily,
    fontStyle = FontStyle.Normal,
    fontSize = size,
    fontWeight = FontWeight.Bold,
    color = color,
    textAlign = TextAlign.Center
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
  modifier: Modifier = Modifier,
  onQueryChange: (String) -> Unit,
  onSearchClick : (String) -> Unit
) {

  var query by remember {
    mutableStateOf("")
  }
  var isActive by remember {
    mutableStateOf(false)
  }
  SearchBar(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 15.dp, horizontal = 15.dp)
      .clip(RoundedCornerShape(10.dp)),

    query = query,
    onQueryChange = { newQuery ->
      query = newQuery
      onQueryChange(newQuery.lowercase())
    },
    onSearch = {
onSearchClick(it)
    },
    active = false,
    onActiveChange = { activeChange ->
      isActive = activeChange

    },
    shape = RoundedCornerShape(10.dp),
    colors = SearchBarDefaults.colors(containerColor = colorResource(id = R.color.SearchContainerColor)),
    trailingIcon = {
      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "SearchIcon",
        tint = Color.Gray,
        modifier = Modifier.size(30.dp)
      )
    },
    placeholder = {
      Text(
        text = "Search",
        modifier = Modifier
          .fillMaxWidth()
          .size(30.dp),
        color = colorResource(id = R.color.SearchHintColor)
      )
    },
    tonalElevation = 0.dp


  ) {

  }
}

@Composable
fun ResultItem(
  modifier: Modifier = Modifier,
  author: String,
  title: String,
  onClick: () -> Unit,
  onFavoriteClick: () -> Unit,
  isFav: Boolean
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(10.dp))
      .clickable {
        onClick()
      }
  ) {

    Row(horizontalArrangement = Arrangement.SpaceBetween) {
      Column(verticalArrangement = Arrangement.SpaceBetween) {

        FavoriteButton(
          modifier = Modifier.padding(8.dp),
          onFavoriteClick = onFavoriteClick,
          isFav = isFav
        )

        Text(
          text = author,
          modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
          style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp
          )
        )
        Text(
          text = title,
          modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
          style = TextStyle(
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 20.sp
          )
        )
      }
    }
  }

}

@Composable
fun FavoriteButton(
  modifier: Modifier = Modifier,
  color: Color = Color.White,
  onFavoriteClick: () -> Unit,
  isFav: Boolean
) {

  var isFavorite by remember { mutableStateOf(isFav) }

  IconToggleButton(
    checked = isFavorite,
    onCheckedChange = {
      onFavoriteClick()
      isFavorite = isFav
    }
  ) {
    Icon(
      tint = color,
      modifier = modifier.graphicsLayer {
        scaleX = 1.3f
        scaleY = 1.3f
      },
      imageVector = if (isFavorite) {
        Icons.Filled.Favorite
      } else {
        Icons.Default.FavoriteBorder
      },
      contentDescription = null
    )
  }

}
