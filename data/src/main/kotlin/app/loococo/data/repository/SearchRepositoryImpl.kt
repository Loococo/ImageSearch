package app.loococo.data.repository

import android.util.Log
import app.loococo.data.model.response.toSearch
import app.loococo.data.remote.manger.SearchDataSource
import app.loococo.domain.model.Search
import app.loococo.domain.model.network.Resource
import app.loococo.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchDataSource: SearchDataSource) :
    SearchRepository {

    override suspend fun search(searchWord: String): Flow<Resource<Search>> = flow {
        Log.e("-------------2","$searchWord")
        searchDataSource.search(searchWord).collect {
            Log.e("---------------","$it")
            when (it) {
                is Resource.Success -> {
                    emit(Resource.Success(it.data.toSearch()))
                }

                is Resource.Error -> {
                    emit(it)
                }
            }
        }
    }
}