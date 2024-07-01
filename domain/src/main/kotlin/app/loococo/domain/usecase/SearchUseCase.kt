package app.loococo.domain.usecase

import android.util.Log
import androidx.paging.PagingData
import app.loococo.domain.model.Search
import app.loococo.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    fun search(keyword: String): Flow<PagingData<Search>> {
        return searchRepository.search(keyword)
    }
}