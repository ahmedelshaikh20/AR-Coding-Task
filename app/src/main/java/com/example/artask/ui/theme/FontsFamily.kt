package com.example.artask.ui.theme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.artask.R

class fontsfamilys {
  companion object {
    val poppinsFamily = FontFamily(
      Font(R.font.poppinsblack, FontWeight.Black),
      Font(R.font.poppinsbold, FontWeight.Bold),
      Font(R.font.poppinsextrabold, FontWeight.ExtraBold),
      Font(R.font.poppinsregular, FontWeight.Normal)
    )
  }
}
