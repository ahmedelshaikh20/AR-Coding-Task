

data class ArticleModel(
  val id: Int?=null,
  val title: String,
  val description: String,
  val author:String?,
  val query: String,
  val isFav: Boolean

)
