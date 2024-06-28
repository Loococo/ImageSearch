package app.loococo.imagesearch.di

import app.loococo.data.remote.api.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    fun providerSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

}
