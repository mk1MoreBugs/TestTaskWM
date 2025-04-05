package com.example.pix.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.pix.ui.navigation.PixNavGraph
import com.example.pix.ui.theme.PixTheme

@Composable
fun PixApp() {
    val navController = rememberNavController()
    PixTheme {
        PixNavGraph(
            navController = navController,
        )
    }
}
