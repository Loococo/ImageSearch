package app.loococo.presentation.search

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import app.loococo.domain.model.error.SearchError
import app.loococo.presentation.component.CircularProgressBar
import app.loococo.presentation.component.ImageSearchCommonScreen
import app.loococo.presentation.component.ImageSearchErrorPopup
import app.loococo.presentation.component.ShowPopupState
import app.loococo.presentation.component.rememberImageSearchErrorPopupState

@Composable
internal fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()

    val keyword by viewModel.keyWordFlow.collectAsStateWithLifecycle()
    val searchWordListState = viewModel.searchWordListFlow.collectAsLazyPagingItems()

    val listState = rememberLazyGridState()

    LaunchedEffect(key1 = keyword) {
        listState.animateScrollToItem(0)
    }
    val showPopupState = rememberImageSearchErrorPopupState()

    if (showPopupState.showPopupState) {
        ImageSearchErrorPopup(
            errorState = showPopupState.errorState,
            onRefresh = {
                searchWordListState.retry()
            },
            onDismissRequest = showPopupState::dismissPopup
        )
    }

    ImageSearchCommonScreen(
        listState,
        searchWordListState,
        viewModel::updateSearchWord,
        viewModel::updateBookmark
    )

    SearchStateScreen(
        keyword = keyword,
        state = searchWordListState.loadState,
        popupState = showPopupState
    )
}

@Composable
fun SearchStateScreen(
    keyword: String,
    state: CombinedLoadStates,
    popupState: ShowPopupState
) {
    CircularProgressBar(false)

    val isLoading = state.refresh is LoadState.Loading || state.append is LoadState.Loading

    if (isLoading && keyword.isNotBlank()) {
        CircularProgressBar(true)
    }
    LaunchedEffect(state) {
        when {
            state.refresh is LoadState.Error -> {
                val refreshError = (state.refresh as LoadState.Error).error as SearchError
                popupState.showPopup(refreshError)
            }

            state.append is LoadState.Error -> {
                val appendError = (state.append as LoadState.Error).error as SearchError
                popupState.showPopup(appendError)
            }
        }
    }
}