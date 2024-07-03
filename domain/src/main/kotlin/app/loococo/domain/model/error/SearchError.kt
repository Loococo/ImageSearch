package app.loococo.domain.model.error

sealed class SearchError : Exception() {
    data object None : SearchError()
    data object NoInternet : SearchError()
    data object ServerError : SearchError()
    data class UnknownError(override val message: String?) : SearchError()
}