package app.loococo.data.remote.manger

import app.loococo.data.model.response.SearchResponse
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun search(searchWord: String): Flow<Resource<SearchResponse>>
}