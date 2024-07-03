package app.loococo.imagesearch.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.loococo.imagesearch.navigation.ImageSearchNavHost
import app.loococo.presentation.component.ImageSearchBottomBar
import app.loococo.presentation.component.ImageSearchNavigationBarItem
import app.loococo.presentation.component.ImageSearchTopBar
import app.loococo.presentation.theme.Black1
import app.loococo.presentation.theme.ImageSearchTheme

@ExperimentalMaterial3Api
@Composable
fun ImageSearchApp(
    appState: ImageSearchAppState = rememberImageSearchState()
) {
    ImageSearchTheme {
        Scaffold(
            topBar = {
                val destination = appState.currentTopLevelDestination
                if (destination != null) {
                    ImageSearchTopBar(title = stringResource(id = destination.titleTextId))
                }
            },
            bottomBar = {
                ImageSearchBottomBar(
                    content = {
                        appState.topLevelDestinations.forEach { destination ->
                            ImageSearchNavigationBarItem(
                                selected = destination == appState.currentTopLevelDestination,
                                onClick = { appState.navigateToTopLevelDestination(destination) },
                                icon = {
                                    Icon(
                                        imageVector = destination.unselectedIcon,
                                        contentDescription = null,
                                    )
                                },
                                selectedIcon = {
                                    Icon(
                                        imageVector = destination.selectedIcon,
                                        contentDescription = null,
                                    )
                                }
                            )
                        }
                    }
                )
            },
            modifier = Modifier.fillMaxSize(),
            containerColor = Black1
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ImageSearchNavHost(appState)
            }
        }
    }
}
