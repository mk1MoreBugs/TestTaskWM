package com.example.pix.ui.watchphoto

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pix.R
import com.example.pix.domain.entity.Picture
import com.example.pix.ui.components.PixScaffold

@Composable
fun WatchPhotoScreen(
    pictureUrl: String,
    pictureTitle: String
) {
    PixScaffold(
        statusbarTitle = pictureTitle
    ) { innerPadding ->
        WatchPhotoScreenContent(
            modifier = Modifier.padding(innerPadding),
            pictureUrl = pictureUrl,
            pictureTitle = pictureTitle
        )
    }
}

@Composable
fun WatchPhotoScreenContent(
    modifier: Modifier,
    pictureUrl: String,
    pictureTitle: String
) {
    AsyncImage(
        modifier = modifier.fillMaxSize(),
        model = ImageRequest.Builder(LocalContext.current)
            .data(pictureUrl)
            .crossfade(true)
            .build(),
        contentDescription = pictureTitle,
        error = painterResource(R.drawable.photo_preview),
        placeholder = painterResource(R.drawable.photo_preview),
    )
}