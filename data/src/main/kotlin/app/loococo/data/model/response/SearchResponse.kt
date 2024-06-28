package app.loococo.data.model.response

import app.loococo.domain.model.Search
import app.loococo.domain.model.SearchContent

data class SearchResponse(
    val meta: Meta,
    val documents: MutableList<Document>?
)

data class Meta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean
)

data class Document(
    val collection: String,
    val thumbnailUrl: String,
    val imageUrl: String,
    val width: Int,
    val height: Int,
    val displaySitename: String,
    val docUrl: String,
    val datetime: String
)

fun SearchResponse.toSearch(): Search {
    return Search(
        list = this.documents?.map { document ->
            SearchContent(
                image = document.imageUrl,
                title = document.displaySitename
            )
        } ?: emptyList()
    )
}
