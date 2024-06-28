package app.loococo.domain.model.network

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: ResourceException) : Resource<Nothing>()
}

sealed class ResourceException {
    data class NetworkException(val error: Throwable) : ResourceException()
    data class HttpException(val code: Int, val errorMessage: String) : ResourceException()
    data object NoDataException : ResourceException()
    data class UnknownException(val error: Throwable) : ResourceException()
}