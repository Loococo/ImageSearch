package app.loococo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.loococo.data.local.database.dao.BookmarkDao
import app.loococo.data.local.database.model.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}