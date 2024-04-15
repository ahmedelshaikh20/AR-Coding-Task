package com.example.domain.usecases

import ArticleModel
import com.example.domain.usecases.repository.ArticleRepository
import javax.inject.Inject

class AddFavouriteArticle @Inject constructor(private val articleRepository: ArticleRepository){

  suspend operator fun invoke(articleModel: ArticleModel) = articleRepository.addFavArticle(articleModel)

}
