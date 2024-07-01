package app.loococo.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import app.loococo.domain.model.Search
import app.loococo.presentation.R
import app.loococo.presentation.search.SearchListScreen

@Composable
fun ImageSearchCommonScreen(
    searchWordList: LazyPagingItems<Search>,
    onChangeText: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        HeightSpacer(height = 10)
        ImageSearchBorderTextField(
            hint = stringResource(id = R.string.search_hint),
            onValueChange = onChangeText
        )

        SearchListScreen(searchWordList)
    }
}