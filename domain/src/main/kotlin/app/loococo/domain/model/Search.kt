package app.loococo.domain.model

data class Search(
    val image: String,
    val description: String,
    val keyword:String,
    val state: Boolean = false
)