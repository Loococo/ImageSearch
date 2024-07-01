package app.loococo.presentation.bookmark

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BookmarkRoute() {
    BookmarkScreen()
}

@Composable
fun BookmarkScreen() {
    val viewModel: BookmarkViewModel = hiltViewModel()
}