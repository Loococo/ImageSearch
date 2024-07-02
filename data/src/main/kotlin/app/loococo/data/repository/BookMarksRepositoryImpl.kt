package app.loococo.data.repository

import app.loococo.data.local.database.dao.BookmarkDao
import app.loococo.data.local.database.model.toBookmarkEntity
import app.loococo.data.local.database.model.toSearch
import app.loococo.domain.model.Search
import app.loococo.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarksRepository {
    override suspend fun insert(search: Search) {
        bookmarkDao.insert(search.toBookmarkEntity())
    }

    override suspend fun delete(id: Int) {
        bookmarkDao.deleteById(id)
    }

    override suspend fun delete(search: Search) {
        bookmarkDao.deleteByKeywordAndImage(search.keyword, search.image)
    }

    override fun getBookmarks(): Flow<List<Search>> {
        return bookmarkDao.getBookmarks().map { bookmarks -> bookmarks.map { it.toSearch() } }
    }

    override fun getBookmarksByKeyword(keyword: String): Flow<List<Search>> {
        return bookmarkDao.getBookmarksByKeyword(keyword).map { bookmarks -> bookmarks.map { it.toSearch() } }
    }
}