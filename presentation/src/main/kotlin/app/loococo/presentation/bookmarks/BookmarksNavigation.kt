package app.loococo.presentation.bookmarks

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val bookmarksRoute = "bookmarks_route"

fun NavGraphBuilder.bookmarksScreen() {
    composable(route = bookmarksRoute) {
        BookmarksRoute()
    }
}

fun NavController.navigateToBookmarks(navOptions: NavOptions) {
    this.navigate(bookmarksRoute, navOptions)
}
