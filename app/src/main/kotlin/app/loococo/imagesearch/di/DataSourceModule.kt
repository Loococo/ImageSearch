package app.loococo.imagesearch.di

import app.loococo.data.remote.manger.SearchDataSource
import app.loococo.data.remote.manger.impl.SearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RemoteDatasourceModule {
    @Binds
    fun provideSearchDataSourceImpl(repository: SearchDataSourceImpl): SearchDataSource
}