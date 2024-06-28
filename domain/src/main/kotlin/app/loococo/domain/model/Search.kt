package app.loococo.domain.model

data class Search(
    val list: List<SearchContent>
)

data class SearchContent(
    val image: String,
    val title: String
)