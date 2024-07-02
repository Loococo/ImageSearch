package app.loococo.presentation.search

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.loococo.presentation.component.CircularProgressBar
import app.loococo.presentation.component.ImageSearchCommonScreen

@Composable
internal fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()

    val keyword by viewModel.keyWordFlow.collectAsStateWithLifecycle()
    val searchWordListState = viewModel.searchWordListFlow.collectAsLazyPagingItems()

    val isInitial = keyword.isEmpty()
    val isEmpty = searchWordListState.itemCount == 0
    val isLoading = searchWordListState.loadState.refresh is LoadState.Loading
    val isError = searchWordListState.loadState.refresh is LoadState.Error

    val listState = rememberLazyGridState()

    LaunchedEffect(key1 = keyword) {
        listState.animateScrollToItem(0)
    }
    ImageSearchCommonScreen(
        listState,
        searchWordListState,
        viewModel::updateSearchWord,
        viewModel::updateBookmark
    )

    when {
        isInitial && isEmpty -> {
        }

        isLoading -> {
            CircularProgressBar()
        }

        isError -> {

        }
    }
}