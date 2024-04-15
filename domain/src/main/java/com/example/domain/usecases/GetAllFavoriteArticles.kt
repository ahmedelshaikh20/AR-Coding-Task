package com.example.domain.usecases

import ArticleModel
import com.example.domain.usecases.repository.ArticleRepository
import javax.inject.Inject

class GetAllFavoriteArticles @Inject constructor(private val articleRepository: ArticleRepository){

  suspend operator fun invoke(query:String) : List<ArticleModel> = articleRepository.getAllFavoriteArticles(query)

}
