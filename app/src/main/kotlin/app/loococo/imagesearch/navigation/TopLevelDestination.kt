package app.loococo.imagesearch.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import app.loococo.presentation.R
import app.loococo.presentation.utils.ImageSearchIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    SEARCH(
        selectedIcon = ImageSearchIcons.Search,
        unselectedIcon = ImageSearchIcons.SearchBorder,
        iconTextId = R.string.search,
        titleTextId = R.string.search
    ),
    BOOKMARKS(
        selectedIcon = ImageSearchIcons.Bookmarks,
        unselectedIcon = ImageSearchIcons.BookmarksBorder,
        iconTextId = R.string.bookmarks,
        titleTextId = R.string.bookmarks
    )
}
