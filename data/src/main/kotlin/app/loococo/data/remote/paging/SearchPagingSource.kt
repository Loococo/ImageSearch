package app.loococo.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.loococo.data.model.response.toSearch
import app.loococo.data.remote.api.SearchApi
import app.loococo.domain.model.Search
import app.loococo.domain.model.error.SearchError
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val searchApi: SearchApi,
    private val keyword: String
) : PagingSource<Int, Search>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        val page = params.key ?: 1
        return try {
            val response = searchApi.search(keyword, page, params.loadSize)
            if (response.isSuccessful) {
                val responseData =
                    response.body()?.documents?.map { it.toSearch(keyword) } ?: emptyList()
                LoadResult.Page(
                    data = responseData,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (response.body()?.meta?.isEnd == true) null else page + 1
                )
            } else {
                LoadResult.Error(SearchError.ServerError)
            }
        } catch (e: Exception) {
            LoadResult.Error(
                when (e) {
                    is IOException -> SearchError.NoInternet
                    is HttpException -> SearchError.ServerError
                    else -> SearchError.UnknownError(e.message)
                }
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}