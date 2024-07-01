package app.loococo.imagesearch.di

import app.loococo.domain.repository.BookMarksRepository
import app.loococo.domain.repository.SearchRepository
import app.loococo.domain.usecase.BookMarksUseCase
import app.loococo.domain.usecase.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchUseCase(repo: SearchRepository): SearchUseCase = SearchUseCase(repo)

    @Provides
    @Singleton
    fun provideBookMarksUseCase(repo: BookMarksRepository): BookMarksUseCase = BookMarksUseCase(repo)
}
