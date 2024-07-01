package app.loococo.presentation.search

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import app.loococo.presentation.component.ImageSearchCommonScreen

@Composable
internal fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()

    val searchWordListState = viewModel.searchWordListFlow.collectAsLazyPagingItems()

    ImageSearchCommonScreen(
        searchWordListState,
        viewModel::updateSearchWord
    )
}