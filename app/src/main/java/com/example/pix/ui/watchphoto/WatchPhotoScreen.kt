package com.example.pix.ui.watchphoto

import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.pix.R
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
    var scale by remember { mutableStateOf(1f) }
    AsyncImage(
        modifier = modifier.fillMaxSize()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).coerceIn(0.5f, 3f)
                }
            },
        model = ImageRequest.Builder(LocalContext.current)
            .data(pictureUrl)
            .crossfade(true)
            .build(),
        contentDescription = pictureTitle,
        error = painterResource(R.drawable.photo_preview),
        placeholder = painterResource(R.drawable.photo_preview),
    )
}
