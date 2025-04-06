package com.example.pix.ui.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pix.domain.entity.Picture
import com.example.pix.ui.searchphotos.SearchPhotosScreen
import com.example.pix.ui.watchphoto.WatchPhotoScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
sealed class Destinations {
    @Serializable
    data object SearchPhotos : Destinations()

    @Serializable
    data class WatchPhoto(
        val pictureUrl: String,
        val pictureTitle: String
    ) : Destinations()
}


@Composable
fun PixNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Destinations = Destinations.SearchPhotos,
    navActions: PixNavigationActions = remember(navController) {
        PixNavigationActions(navController = navController)
    }
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Destinations.SearchPhotos> {
            SearchPhotosScreen(
                onNavigateTo = navActions::navigateTo,
            )
        }

        composable<Destinations.WatchPhoto> { backStackEntry ->
            val watchPhotoDestination: Destinations.WatchPhoto = backStackEntry.toRoute()
            WatchPhotoScreen(
                pictureUrl = watchPhotoDestination.pictureUrl,
                pictureTitle = watchPhotoDestination.pictureTitle
            )
        }
    }
}
