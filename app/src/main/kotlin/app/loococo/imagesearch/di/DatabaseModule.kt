package app.loococo.imagesearch.di

import android.content.Context
import androidx.room.Room
import app.loococo.data.local.database.AppDatabase
import app.loococo.data.local.database.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "bookmark_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideImageSearchDao(database: AppDatabase): BookmarkDao {
        return database.bookmarkDao()
    }
}