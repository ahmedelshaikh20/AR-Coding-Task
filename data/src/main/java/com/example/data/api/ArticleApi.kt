package com.example.data.api

import com.example.data.model.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleApi {

  @GET("everything/")
  suspend fun getAllArticles(
    @Query("q") query: String, @Query("apikey") apikey: String = API_KEY
  ):ArticlesResponse

  companion object {
    const val API_KEY = "cbf5a67fe65c45b18dec61c2f06c2790"
    const val BASE_URL = "https://newsapi.org/v2/"
  }


}
