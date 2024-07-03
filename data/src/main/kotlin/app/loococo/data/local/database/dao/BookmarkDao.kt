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

    @Query("DELETE FROM bookmark WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM bookmark WHERE keyword = :keyword AND image = :image")
    suspend fun deleteByKeywordAndImage(keyword: String, image: String)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmark WHERE keyword = :keyword")
    fun getBookmarksByKeyword(keyword: String): Flow<List<BookmarkEntity>>
}