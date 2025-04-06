package com.example.pix.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun PixScaffold(
    statusbarTitle: String,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    onSearchTextButtonClick: () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            PixStatusBar(
                title = statusbarTitle,
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                onSearchTextButtonClick = onSearchTextButtonClick,
            )
        },
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        content = content
    )
}
