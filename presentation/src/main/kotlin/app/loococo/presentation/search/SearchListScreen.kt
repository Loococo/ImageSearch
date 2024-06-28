package app.loococo.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.loococo.presentation.component.ImageSearchLabelText
import app.loococo.presentation.theme.Red10

@Composable
fun SearchListScreen() {
    val list = listOf("1", "2", "3", "4", "5")

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(list) { item ->
            SearchItem(item)
        }
    }
}

@Composable
fun SearchItem(item: String) {
    Column(modifier = Modifier.padding(5.dp)) {
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .background(Red10)
        )
        ImageSearchLabelText(text = item)
    }
}