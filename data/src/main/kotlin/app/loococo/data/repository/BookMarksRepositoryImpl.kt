package app.loococo.data.repository

import app.loococo.data.local.database.dao.BookmarkDao
import app.loococo.data.local.database.model.toBookmarkEntity
import app.loococo.data.local.database.model.toSearch
import app.loococo.domain.model.Search
import app.loococo.domain.repository.BookMarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookMarksRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookMarksRepository {
    override suspend fun insert(search: Search) {
        bookmarkDao.insert(search.toBookmarkEntity())
    }

    override fun getBookmarks(): Flow<List<Search>> {
        return bookmarkDao.getBookmarks().map { bookmarks -> bookmarks.map { it.toSearch() } }
    }
}