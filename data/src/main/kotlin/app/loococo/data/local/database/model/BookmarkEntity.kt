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
        image = image,
        description = description,
        keyword = keyword
    )
}

fun Search.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(
        image = image,
        description = description,
        keyword = keyword
    )
}