package app.loococo.imagesearch.di

import app.loococo.data.repository.SearchRepositoryImpl
import app.loococo.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Binds
    fun provideSearchRepositoryImpl(repository: SearchRepositoryImpl): SearchRepository
}