package app.loococo.imagesearch.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import app.loococo.imagesearch.navigation.TopLevelDestination
import app.loococo.presentation.bookmark.bookmarkRoute
import app.loococo.presentation.bookmark.navigateToBookmarks
import app.loococo.presentation.search.navigateToSearch
import app.loococo.presentation.search.searchRoute

@Composable
fun rememberImageSearchState(
    navController: NavHostController = rememberNavController()
): ImageSearchAppState {
    return remember(
        navController,
    ) {
        ImageSearchAppState(
            navController = navController
        )
    }
}

@Stable
class ImageSearchAppState(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            searchRoute -> TopLevelDestination.SEARCH
            bookmarkRoute -> TopLevelDestination.BOOKMARKS
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace(sectionName = "Navigation: ${topLevelDestination.name}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when(topLevelDestination) {
                TopLevelDestination.SEARCH -> {
                    navController.navigateToSearch(topLevelNavOptions)
                }
                TopLevelDestination.BOOKMARKS -> {
                    navController.navigateToBookmarks(topLevelNavOptions)
                }
            }
        }
    }
}