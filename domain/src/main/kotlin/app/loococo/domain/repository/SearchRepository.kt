package app.loococo.domain.repository

import androidx.paging.PagingData
import app.loococo.domain.model.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SearchRepository {
    fun search(keyword: String): Flow<PagingData<Search>>
}