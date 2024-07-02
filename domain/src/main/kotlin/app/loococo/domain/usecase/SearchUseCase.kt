package app.loococo.domain.usecase

import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import app.loococo.domain.model.Search
import app.loococo.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository,
    private val bookMarksUseCase: BookMarksUseCase,
    private val scope: CoroutineScope
) {
    fun search(keyword: String): Flow<PagingData<Search>> {
        return flow {
            if (keyword.isBlank()) {
                emit(PagingData.empty())
            } else {
                val pagingFlow = searchRepository.search(keyword).cachedIn(scope)
                combine(
                    pagingFlow,
                    bookMarksUseCase.getBookmarksByKeyword(keyword)
                ) { pagingData, bookmarks ->
                    val bookmarkMap = bookmarks.associateBy { it.image }
                    pagingData.map { searchItem ->
                        bookmarkMap[searchItem.image]?.let { bookmark ->
                            searchItem.copy(id = bookmark.id, state = true)
                        } ?: searchItem.copy(state = false)
                    }
                }.collect { emit(it) }
            }
        }
    }
}