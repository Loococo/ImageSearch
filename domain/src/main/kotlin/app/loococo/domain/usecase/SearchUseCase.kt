package app.loococo.domain.usecase

import android.util.Log
import app.loococo.domain.model.Search
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun search(searchWord: String): Flow<Resource<Search>> {
        Log.e("-------------1","$searchWord")
        return searchRepository.search(searchWord)
    }
}