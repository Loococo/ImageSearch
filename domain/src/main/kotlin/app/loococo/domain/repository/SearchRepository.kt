package app.loococo.domain.repository

import androidx.paging.PagingData
import app.loococo.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(searchWord: String): Flow<PagingData<Search>>
}