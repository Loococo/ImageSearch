package app.loococo.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.loococo.domain.model.Search

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: String,
    val description: String,
    val keyword: String
)


fun BookmarkEntity.toSearch(): Search {
    return Search(
        id = id,
        image = image,
        description = description,
        keyword = keyword,
        state = true
    )
}

fun Search.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id,
        image = image,
        description = description,
        keyword = keyword
    )
}