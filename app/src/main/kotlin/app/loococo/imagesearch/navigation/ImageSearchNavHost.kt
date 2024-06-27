package app.loococo.imagesearch.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import app.loococo.imagesearch.ui.ImageSearchAppState
import app.loococo.presentation.bookmarks.bookmarksScreen
import app.loococo.presentation.search.searchRoute
import app.loococo.presentation.search.searchScreen

@Composable
fun ImageSearchNavHost(
    appState: ImageSearchAppState
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = searchRoute
    ) {
        searchScreen()
        bookmarksScreen()
    }
}