package app.loococo.presentation.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchRoute = "search_route"

fun NavGraphBuilder.searchScreen() {
    composable(route = searchRoute) {
        SearchRoute()
    }
}

fun NavController.navigateToSearch(navOptions: NavOptions) {
    this.navigate(searchRoute, navOptions)
}

