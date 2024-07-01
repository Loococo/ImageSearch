package app.loococo.domain.usecase

import app.loococo.domain.model.Search
import app.loococo.domain.repository.BookMarksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookMarksUseCase @Inject constructor(private val bookMarksRepository: BookMarksRepository) {
    suspend fun insert(search: Search) {
        bookMarksRepository.insert(search)
    }

    fun getBookmarks(): Flow<List<Search>> {
        return bookMarksRepository.getBookmarks()
    }
}