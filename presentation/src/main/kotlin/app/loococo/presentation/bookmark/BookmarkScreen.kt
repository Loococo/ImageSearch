package app.loococo.presentation.bookmark

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import app.loococo.presentation.component.ImageSearchCommonScreen

@Composable
internal fun BookmarkRoute() {
    BookmarkScreen()
}

@Composable
fun BookmarkScreen() {
    val viewModel: BookmarkViewModel = hiltViewModel()

    val searchWordListState by viewModel.searchWordListFlow.collectAsStateWithLifecycle()

    val listState = rememberLazyGridState()

    ImageSearchCommonScreen(
        listState,
        searchWordListState,
        viewModel::updateSearchWord,
        viewModel::deleteBookmark
    )
}