package app.loococo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import app.loococo.data.remote.api.SearchApi
import app.loococo.data.remote.paging.SearchPagingSource
import app.loococo.domain.model.Search
import app.loococo.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchApi: SearchApi
) : SearchRepository {

    override fun search(keyword: String): Flow<PagingData<Search>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { SearchPagingSource(searchApi, keyword) }
        ).flow
    }
}