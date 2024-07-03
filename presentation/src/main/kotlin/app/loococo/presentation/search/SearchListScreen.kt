package app.loococo.presentation.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import app.loococo.domain.model.Search
import app.loococo.presentation.R
import app.loococo.presentation.component.ImageSearchIconButton
import app.loococo.presentation.component.ImageSearchLabelText
import app.loococo.presentation.component.ImageSearchTitleText
import app.loococo.presentation.theme.Black50
import app.loococo.presentation.theme.Red
import app.loococo.presentation.theme.White
import app.loococo.presentation.utils.ImageSearchIcons
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun SearchListScreen(
    listState: LazyGridState,
    list: LazyPagingItems<Search>,
    onBookMark: (Search) -> Unit
) {
    if (list.itemCount == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ImageSearchTitleText(text = stringResource(id = R.string.search_noting))
        }
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {
            items(count = list.itemCount) { index ->
                list[index]?.let { search ->
                    SearchItem(search, onBookMark)
                }
            }
        }
    }
}

@Composable
fun SearchListScreen(
    listState: LazyGridState,
    list: List<Search>,
    onBookMark: (Search) -> Unit
) {
    if (list.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            ImageSearchTitleText(text = stringResource(id = R.string.bookmark_noting))
        }
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(2), state = listState) {
            items(count = list.size) { index ->
                SearchItem(list[index], onBookMark)
            }
        }
    }
}

@Composable
fun SearchItem(
    item: Search,
    onBookMark: (Search) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .background(White)
    ) {
        Box {
            SearchImageLoad(item.image)
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .background(Black50, CircleShape)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                ImageSearchIconButton(
                    modifier = Modifier
                        .padding(5.dp),
                    size = 22.dp,
                    color = Red,
                    icon = if (item.state) ImageSearchIcons.Bookmarks else ImageSearchIcons.BookmarksBorder,
                    description = "bookmark"
                ) {
                    onBookMark(item)
                }
            }
        }
        Box(modifier = Modifier.padding(10.dp)) {
            ImageSearchLabelText(text = item.description)
        }
    }
}

@Composable
fun SearchImageLoad(image: String) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(image)
            .size(coil.size.Size.ORIGINAL)
            .build()
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is AsyncImagePainter.State.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        imageVector = ImageSearchIcons.Warning,
                        contentDescription = "image load error"
                    )
                }
            }

            else -> {
                Image(
                    painter = painter,
                    contentDescription = "search image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}