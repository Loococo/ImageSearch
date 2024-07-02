package app.loococo.imagesearch.di

import app.loococo.data.repository.BookmarksRepositoryImpl
import app.loococo.data.repository.SearchRepositoryImpl
import app.loococo.domain.repository.BookmarksRepository
import app.loococo.domain.repository.SearchRepository
import app.loococo.domain.usecase.BookMarksUseCase
import app.loococo.domain.usecase.SearchUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchUseCase(
        repo: SearchRepository,
        useCase: BookMarksUseCase,
        scope: CoroutineScope
    ): SearchUseCase = SearchUseCase(repo, useCase, scope)

    @Provides
    @Singleton
    fun provideBookMarksUseCase(repo: BookmarksRepository): BookMarksUseCase =
        BookMarksUseCase(repo)
}