package app.loococo.data.remote.manger.impl

import app.loococo.data.model.network.suspendResponseResult
import app.loococo.data.model.response.SearchResponse
import app.loococo.data.remote.api.SearchApi
import app.loococo.data.remote.manger.SearchDataSource
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchDataSource {
    override suspend fun search(searchWord: String): Flow<Resource<SearchResponse>> {
        return suspendResponseResult { searchApi.search(searchWord) }
    }
}