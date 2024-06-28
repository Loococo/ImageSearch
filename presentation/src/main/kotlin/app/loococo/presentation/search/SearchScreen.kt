package app.loococo.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import app.loococo.presentation.R
import app.loococo.presentation.component.HeightSpacer
import app.loococo.presentation.component.ImageSearchBorderTextField

@Composable
internal fun SearchRoute() {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxSize()) {

        HeightSpacer(height = 10)
        ImageSearchBorderTextField(
            hint = stringResource(id = R.string.search_hint),
            onValueChange = viewModel::updateSearchWord
        )

        SearchListScreen()
    }
}