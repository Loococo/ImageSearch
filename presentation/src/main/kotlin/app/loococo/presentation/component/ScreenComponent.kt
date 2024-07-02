package app.loococo.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import app.loococo.domain.model.Search
import app.loococo.presentation.R
import app.loococo.presentation.search.SearchListScreen

@Composable
fun ImageSearchCommonScreen(
    listState: LazyGridState,
    searchWordList: LazyPagingItems<Search>,
    onChangeText: (String) -> Unit,
    onBookMark: (Search) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        HeightSpacer(height = 10)
        ImageSearchBorderTextField(
            hint = stringResource(id = R.string.search_hint),
            onValueChange = onChangeText
        )

        SearchListScreen(
            listState,
            searchWordList,
            onBookMark
        )
    }
}

@Composable
fun ImageSearchCommonScreen(
    listState: LazyGridState,
    searchWordList: List<Search>,
    onChangeText: (String) -> Unit,
    onBookMark: (Search) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        HeightSpacer(height = 10)
        ImageSearchBorderTextField(
            hint = stringResource(id = R.string.search_hint),
            onValueChange = onChangeText
        )

        SearchListScreen(
            listState,
            searchWordList,
            onBookMark
        )
    }
}