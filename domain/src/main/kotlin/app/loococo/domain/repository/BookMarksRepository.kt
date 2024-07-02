package app.loococo.domain.repository

import app.loococo.domain.model.Search
import kotlinx.coroutines.flow.Flow

interface BookmarksRepository {

    suspend fun insert(search: Search)

    suspend fun delete(id: Int)

    suspend fun delete(search: Search)

    fun getBookmarks(): Flow<List<Search>>

    fun getBookmarksByKeyword(keyword: String): Flow<List<Search>>
}