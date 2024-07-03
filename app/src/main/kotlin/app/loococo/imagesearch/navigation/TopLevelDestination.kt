package app.loococo.imagesearch.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import app.loococo.presentation.R
import app.loococo.presentation.utils.ImageSearchIcons

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val titleTextId: Int,
) {
    SEARCH(
        selectedIcon = ImageSearchIcons.Search,
        unselectedIcon = ImageSearchIcons.SearchBorder,
        titleTextId = R.string.search
    ),
    BOOKMARKS(
        selectedIcon = ImageSearchIcons.Bookmarks,
        unselectedIcon = ImageSearchIcons.BookmarksBorder,
        titleTextId = R.string.bookmark
    )
}
