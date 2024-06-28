package app.loococo.domain.repository

import app.loococo.domain.model.Search
import app.loococo.domain.model.network.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(searchWord: String): Flow<Resource<Search>>
}