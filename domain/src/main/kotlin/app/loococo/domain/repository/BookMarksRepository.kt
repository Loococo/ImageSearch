package app.loococo.domain.repository

import app.loococo.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface BookMarksRepository {

    suspend fun insert(search: Search)

    fun getBookmarks(): Flow<List<Search>>
}