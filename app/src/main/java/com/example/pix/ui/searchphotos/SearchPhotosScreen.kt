package com.example.pix.ui.searchphotos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pix.R
import com.example.pix.ui.components.PixScaffold
import com.example.pix.ui.navigation.Destinations
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pix.domain.entity.Picture
import com.example.pix.ui.theme.PixTheme

@Composable
fun SearchPhotosScreen(
    viewModel: SearchPhotosViewModel = hiltViewModel(),
    onNavigateTo: (Destinations) -> Unit,
) {
    PixScaffold(
        statusbarTitle = stringResource(R.string.search_photos)
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        SearchPhotosContent(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState,
            onNavigateTo =onNavigateTo,
            onLoadMoreButtonClick = viewModel::addPhotosToUiState
        )
    }
}

@Composable
fun SearchPhotosContent(
    modifier: Modifier = Modifier,
    uiState: SearchPhotosUiState,
    onNavigateTo: (Destinations) -> Unit,
    onLoadMoreButtonClick: () -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val listPhotos = uiState.listPhotos
        for (index in listPhotos.indices step 2) {
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    PhotoCard(
                        picture = listPhotos[index],
                        onNavigateTo = onNavigateTo,
                    )
                    if (index + 1 < listPhotos.size) {
                        PhotoCard(
                            picture = listPhotos[index + 1],
                            onNavigateTo = onNavigateTo,
                        )
                    }
                }

            }
        }
        item {
            Spacer(Modifier.size(10.dp))
        }
        item {
            Button(
                onClick = onLoadMoreButtonClick
            ) {
                Text(
                    text = stringResource(R.string.load_more_button)
                )
            }
        }
    }
}

@Composable
fun PhotoCard(
    picture: Picture,
    onNavigateTo: (Destinations) -> Unit,
) {
    val cardSize = 100.dp
    Card(
        modifier = Modifier.padding(10.dp).size(cardSize),
        onClick = {
            onNavigateTo(
                Destinations.WatchPhoto(
                    pictureUrl = picture.createUrl(sizeSuffix = "b"),
                    pictureTitle = picture.title
                )
            )
        }
    ) {
        AsyncImage(
            modifier = Modifier.size(cardSize),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(LocalContext.current)
                .data(picture.createUrl())
                .crossfade(true)
                .build(),
            contentDescription = picture.title,
            error = painterResource(R.drawable.photo_preview),
            placeholder = painterResource(R.drawable.photo_preview),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SearchPhotosContentPreview() {
    PixTheme {
        SearchPhotosContent(
            uiState = SearchPhotosUiState(
                listPhotos = listOf(
                    Picture(
                        title = "title",
                        server = "server",
                        id = "id",
                        secret = "secret",
                    ),
                    Picture(
                        title = "title",
                        server = "server",
                        id = "id",
                        secret = "secret",
                    ),
                    Picture(
                        title = "title",
                        server = "server",
                        id = "id",
                        secret = "secret",
                    ),
                    Picture(
                        title = "title",
                        server = "server",
                        id = "id",
                        secret = "secret",
                    ),
                ),
                pageCount = 1
            ),
            onNavigateTo = {},
            onLoadMoreButtonClick = {},
        )
    }
}