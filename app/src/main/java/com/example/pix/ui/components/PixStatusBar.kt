package com.example.pix.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PixStatusBar(
    title: String,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchTextButtonClick: () -> Unit
) {
    var enableSearchField by remember { mutableStateOf(false) }
    TopAppBar(
        modifier = Modifier.height(120.dp),
        title = {
            if (enableSearchField) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    textStyle = MaterialTheme.typography.bodyMedium
                )
            } else {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    if (enableSearchField) {
                        onSearchTextButtonClick()
                    } else {
                        enableSearchField = true
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Sharp.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
    )
}