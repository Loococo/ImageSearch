package app.loococo.data.remote.api

import app.loococo.data.model.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/v2/search/image")
    suspend fun search(
        @Query("query") searchWord: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<SearchResponse>
}