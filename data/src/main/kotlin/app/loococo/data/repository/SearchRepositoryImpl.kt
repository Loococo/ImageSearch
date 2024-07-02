package app.loococo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import app.loococo.data.remote.api.SearchApi
import app.loococo.data.remote.paging.SearchPagingSource
import app.loococo.domain.model.Search
import app.loococo.domain.repository.SearchRepository
import app.loococo.domain.usecase.BookMarksUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi,
    private val bookMarksUseCase: BookMarksUseCase,
    private val scope: CoroutineScope,
) : SearchRepository {

    override fun search(keyword: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SearchPagingSource(searchApi, keyword) }
        ).flow
    }
}

//    override fun search(keyword: String): Flow<PagingData<Search>> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = { SearchPagingSource(searchApi, keyword) }
//        ).flow.combine(bookMarksUseCase.getBookmarksByKeyword(keyword)) { pagingData, bookmarks ->
//            pagingData.map { searchItem ->
//                val bookmark = bookmarks.find { it.image == searchItem.image }
//                if (bookmark != null) {
//                    searchItem.copy(id = bookmark.id, state = true)
//                } else {
//                    searchItem.copy(state = false)
//                }
//            }
//        }
//    }
//}
//class SearchRepositoryImpl @Inject constructor(
//    private val searchApi: SearchApi
//) : SearchRepository {
//
//    override fun search(keyword: String): Flow<PagingData<Search>> {
//        return Pager(
//            config = PagingConfig(pageSize = 20),
//            pagingSourceFactory = { SearchPagingSource(searchApi, keyword) }
//        ).flow
//    }
//
//}
