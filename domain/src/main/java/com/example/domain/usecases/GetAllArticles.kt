package com.example.domain.usecases

import ArticleModel
import com.example.domain.repository.ArticleRepository
import javax.inject.Inject

class GetAllArticles @Inject constructor(private val articleRepository: ArticleRepository){

  suspend operator fun invoke(query:String) : List<ArticleModel> = articleRepository.getAllArticles(query)

}
