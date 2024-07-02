package app.loococo.domain.usecase

import app.loococo.domain.model.Search
import app.loococo.domain.repository.BookmarksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BookMarksUseCase @Inject constructor(private val bookMarksRepository: BookmarksRepository) {

    suspend fun insert(search: Search) {
        bookMarksRepository.insert(search)
    }

    suspend fun delete(id: Int) {
        bookMarksRepository.delete(id)
    }

    suspend fun delete(search: Search) {
        bookMarksRepository.delete(search)
    }

    fun getBookmarks(): Flow<List<Search>> {
        return bookMarksRepository.getBookmarks()
    }

    fun getBookmarksByKeyword(keyword: String): Flow<List<Search>> {
        return if (keyword.isBlank()) {
            bookMarksRepository.getBookmarks()
        } else {
            bookMarksRepository.getBookmarksByKeyword(keyword)

        }
    }
}