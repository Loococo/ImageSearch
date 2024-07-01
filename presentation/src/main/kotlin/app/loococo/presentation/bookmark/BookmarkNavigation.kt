package app.loococo.presentation.bookmark

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val bookmarkRoute = "bookmark_route"

fun NavGraphBuilder.bookmarkScreen() {
    composable(route = bookmarkRoute) {
        BookmarkRoute()
    }
}

fun NavController.navigateToBookmarks(navOptions: NavOptions) {
    this.navigate(bookmarkRoute, navOptions)
}
