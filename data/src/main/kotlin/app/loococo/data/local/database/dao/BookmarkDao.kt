package app.loococo.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.loococo.data.local.database.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert
    suspend fun insert(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): Flow<List<BookmarkEntity>>

}